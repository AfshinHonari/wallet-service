package ir.ahs441.wallet.dataaccess.repository;

import ir.ahs441.wallet.dataaccess.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT SUM(T.amount) FROM TransactionEntity T WHERE T.createdAt > :startDate AND T.createdAt < :endDate")
    BigDecimal calculateTotalAmountOfTransactions(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(T.amount) FROM TransactionEntity T WHERE T.createdAt > :startDate AND T.createdAt < :endDate AND T.type = :type")
    BigDecimal calculateTotalAmountOfTransactions(LocalDateTime startDate, LocalDateTime endDate, TransactionEntity.TransactionType type);
}
