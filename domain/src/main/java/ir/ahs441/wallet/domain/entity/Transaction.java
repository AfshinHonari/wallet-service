package ir.ahs441.wallet.domain.entity;

import ir.ahs441.wallet.domain.valueobject.TransactionId;
import ir.ahs441.common.domain.entity.BaseEntity;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
public class Transaction extends BaseEntity<TransactionId> {

    private final BigDecimal amount;

    private final TransactionType type;

    private final User user;

    private final LocalDateTime createdAt;

    public Transaction(BigDecimal amount, TransactionType type, User user) {
        super();
        this.amount = amount;
        this.type = type;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public enum TransactionType {
        DEPOSIT,
        WITHDRAW
    }

}
