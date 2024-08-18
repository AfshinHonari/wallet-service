package ir.ahs441.wallet.domain.entity;

import ir.ahs441.wallet.domain.exception.WalletServiceDomainException;
import ir.ahs441.wallet.domain.valueobject.UserId;
import ir.ahs441.common.domain.entity.AggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
public class User extends AggregateRoot<UserId> {

    private BigDecimal balance;

    private Set<Transaction> transactions = new LinkedHashSet<>();

    private Long version;

    public Transaction doTransaction(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new WalletServiceDomainException("invalid transaction amount");
        }
        if (balance.add(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new WalletServiceDomainException("insufficient balance");
        }
        setBalance(balance.add(amount));

        Transaction.TransactionType transactionType = amount.compareTo(BigDecimal.ZERO) < 0
                ? Transaction.TransactionType.WITHDRAW
                : Transaction.TransactionType.DEPOSIT;

        var transaction = new Transaction(amount.abs(), transactionType, this);
        transactions.add(transaction);

        return transaction;
    }



}
