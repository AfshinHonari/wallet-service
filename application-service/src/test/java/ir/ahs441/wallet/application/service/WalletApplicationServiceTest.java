package ir.ahs441.wallet.application.service;

import ir.ahs441.wallet.application.service.dto.DoTransactionCommand;
import ir.ahs441.wallet.application.service.dto.DoTransactionResponse;
import ir.ahs441.wallet.application.service.dto.GetBalanceQuery;
import ir.ahs441.wallet.application.service.dto.GetBalanceResponse;
import ir.ahs441.wallet.domain.entity.Transaction;
import ir.ahs441.wallet.domain.entity.User;
import ir.ahs441.wallet.domain.exception.WalletServiceDomainException;
import ir.ahs441.wallet.domain.repository.TransactionsRepository;
import ir.ahs441.wallet.domain.repository.UsersRepository;
import ir.ahs441.wallet.domain.valueobject.TransactionId;
import ir.ahs441.wallet.domain.valueobject.UserId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WalletApplicationServiceTest {

    public Transaction buildTransaction(User user) {
        var transaction = new Transaction(BigDecimal.TEN, Transaction.TransactionType.WITHDRAW, user);
        transaction.setId(new TransactionId(1L));
        return transaction;
    }

    // Successfully process a deposit transaction and update user balance
    @Test
    public void testSuccessfulDepositTransaction() {
        UsersRepository usersRepository = mock(UsersRepository.class);
        TransactionsRepository transactionsRepository = mock(TransactionsRepository.class);
        User user = new User();
        user.setId(new UserId(1L));
        user.setBalance(new BigDecimal("50.00"));
        when(usersRepository.findByIdOrCreate(any(UserId.class))).thenReturn(user);

        var transaction = buildTransaction(user);
        when(transactionsRepository.save(any())).thenReturn(transaction);

        WalletApplicationService service = new WalletApplicationService(usersRepository, transactionsRepository);
        DoTransactionCommand command = new DoTransactionCommand(1L, new BigDecimal("100.00"));
        DoTransactionResponse response = service.doTransaction(command);

        assertEquals(service.generateReferenceNumber(transaction.getId().getValue()), response.referenceId().longValue());
        assertEquals(new BigDecimal("150.00"), user.getBalance());
        verify(transactionsRepository).save(user.getTransactions().stream().findFirst().orElseThrow());
    }

    // Successfully process a withdrawal transaction and update user balance
    @Test
    public void testSuccessfulWithdrawalTransaction() {
        UsersRepository usersRepository = mock(UsersRepository.class);
        TransactionsRepository transactionsRepository = mock(TransactionsRepository.class);
        User user = new User();
        user.setId(new UserId(1L));
        user.setBalance(new BigDecimal("100.00"));
        when(usersRepository.findByIdOrCreate(any(UserId.class))).thenReturn(user);

        var transaction = buildTransaction(user);
        when(transactionsRepository.save(any())).thenReturn(transaction);

        WalletApplicationService service = new WalletApplicationService(usersRepository, transactionsRepository);
        DoTransactionCommand command = new DoTransactionCommand(1L, new BigDecimal("-50.00"));
        DoTransactionResponse response = service.doTransaction(command);

        assertEquals(service.generateReferenceNumber(transaction.getId().getValue()), response.referenceId().longValue());
        assertEquals(new BigDecimal("50.00"), user.getBalance());
        verify(transactionsRepository).save(user.getTransactions().stream().findFirst().orElseThrow());
    }

    // Attempt to withdraw an amount greater than the current balance
    @Test
    public void testWithdrawAmountGreaterThanBalance() {
        UsersRepository usersRepository = mock(UsersRepository.class);
        TransactionsRepository transactionsRepository = mock(TransactionsRepository.class);
        User user = new User();
        user.setId(new UserId(1L));
        user.setBalance(new BigDecimal("50.00"));
        when(usersRepository.findByIdOrCreate(any(UserId.class))).thenReturn(user);
        when(transactionsRepository.save(any())).thenReturn(buildTransaction(user));

        WalletApplicationService service = new WalletApplicationService(usersRepository, transactionsRepository);
        DoTransactionCommand command = new DoTransactionCommand(1L, new BigDecimal("-100.00"));

        Exception exception = assertThrows(WalletServiceDomainException.class, () -> {
            service.doTransaction(command);
        });

        //this is hard coded only for demo purpose
        String expectedMessage = "insufficient balance";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Process a transaction with a zero amount
    @Test
    public void testTransactionWithZeroAmount() {
        UsersRepository usersRepository = mock(UsersRepository.class);
        TransactionsRepository transactionsRepository = mock(TransactionsRepository.class);
        User user = new User();
        user.setId(new UserId(1L));
        user.setBalance(new BigDecimal("50.00"));
        when(usersRepository.findByIdOrCreate(any(UserId.class))).thenReturn(user);
        when(transactionsRepository.save(any())).thenReturn(buildTransaction(user));

        WalletApplicationService service = new WalletApplicationService(usersRepository, transactionsRepository);
        DoTransactionCommand command = new DoTransactionCommand(1L, BigDecimal.ZERO);
        Exception exception = assertThrows(WalletServiceDomainException.class, () -> service.doTransaction(command));

        //this is hard coded only for demo purpose
        String expectedMessage = "invalid transaction amount";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void testSuccessfullyRetrievesBalanceForExistingUser() {
        UsersRepository usersRepository = mock(UsersRepository.class);
        TransactionsRepository transactionsRepository = mock(TransactionsRepository.class);
        WalletApplicationService walletApplicationService = new WalletApplicationService(usersRepository, transactionsRepository);
        UserId userId = new UserId(1L);
        User user = new User();
        user.setBalance(new BigDecimal("100.00"));

        when(usersRepository.findByIdOrCreate(userId)).thenReturn(user);

        GetBalanceQuery query = new GetBalanceQuery(1L);
        GetBalanceResponse response = walletApplicationService.getUserBalance(query);

        assertNotNull(response);
        assertEquals(new BigDecimal("100.00"), response.balance());
    }

}
