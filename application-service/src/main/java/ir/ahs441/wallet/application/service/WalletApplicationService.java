package ir.ahs441.wallet.application.service;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import ir.ahs441.wallet.application.service.dto.DoTransactionCommand;
import ir.ahs441.wallet.application.service.dto.DoTransactionResponse;
import ir.ahs441.wallet.application.service.dto.GetBalanceQuery;
import ir.ahs441.wallet.application.service.dto.GetBalanceResponse;
import ir.ahs441.wallet.domain.entity.User;
import ir.ahs441.wallet.domain.repository.TransactionsRepository;
import ir.ahs441.wallet.domain.repository.UsersRepository;
import ir.ahs441.wallet.domain.valueobject.UserId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Log4j2
@Service
@RequiredArgsConstructor
@Validated
public class WalletApplicationService {

    private final UsersRepository usersRepository;
    private final TransactionsRepository transactionsRepository;
    private static final ULocale PERSIAN_LOCALE = new ULocale("fa_IR@calendar=persian");

    @Transactional
    public DoTransactionResponse doTransaction(@Valid DoTransactionCommand doTransactionCommand) {
        User user = usersRepository.findByIdOrCreate(new UserId(doTransactionCommand.userId()));
        log.info("A new transaction has been requested: {}", doTransactionCommand);
        // In production and real-world applications, we should generate a reference number
        // with incremental resettable logic.
        var transaction = user.doTransaction(doTransactionCommand.amount());
        transaction = transactionsRepository.save(transaction);
        return new DoTransactionResponse(generateReferenceNumber(transaction.getId().getValue()));
    }

    @Transactional(readOnly = true)
    public GetBalanceResponse getUserBalance(@Valid GetBalanceQuery getBalanceQuery) {
        User user = usersRepository.findByIdOrCreate(new UserId(getBalanceQuery.userId()));
        return new GetBalanceResponse(user.getBalance());
    }

    public Long generateReferenceNumber(Long transactionId) {

        Calendar persianCalendar = Calendar.getInstance(PERSIAN_LOCALE);
        int year = persianCalendar.get(Calendar.YEAR);
        int month = persianCalendar.get(Calendar.MONTH) + 1; // Month is 0-based in Calendar

        return Long.parseLong(String.format("%04d%02d%08d", year, month, transactionId));
    }


}
