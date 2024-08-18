package ir.ahs441.wallet.dataaccess.adapter;

import ir.ahs441.wallet.dataaccess.mapper.TransactionMapper;
import ir.ahs441.wallet.dataaccess.repository.TransactionJpaRepository;
import ir.ahs441.wallet.domain.entity.Transaction;
import ir.ahs441.wallet.domain.repository.TransactionsRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionsRepositoryImpl implements TransactionsRepository {

    private final TransactionJpaRepository transactionJpaRepository;
    private final TransactionMapper transactionMapper;
    private final EntityManager entityManager;

    @Override
    public Transaction save(Transaction transaction) {
        var entity = entityManager.merge(transactionMapper.mapToTransactionEntity(transaction));
        return transactionMapper.mapToTransaction(entity);
    }

    @Override
    public BigDecimal calculateTotalAmountOfTransactions(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionJpaRepository
                .calculateTotalAmountOfTransactions(startDate, endDate);
    }



    @Override
    public BigDecimal calculateTotalAmountOfTransactions(LocalDateTime startDate, LocalDateTime endDate, Transaction.TransactionType type) {
        return transactionJpaRepository
                .calculateTotalAmountOfTransactions(startDate, endDate, transactionMapper.mapTransactionType(type));
    }
}
