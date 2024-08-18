package ir.ahs441.wallet.domain.repository;

import ir.ahs441.wallet.domain.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionsRepository {

    Transaction save(Transaction transaction);

    BigDecimal calculateTotalAmountOfTransactions(LocalDateTime startDate, LocalDateTime endDate);

    BigDecimal calculateTotalAmountOfTransactions(LocalDateTime startDate, LocalDateTime endDate, Transaction.TransactionType type);

}
