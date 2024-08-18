package ir.ahs441.wallet.application.service;

import ir.ahs441.wallet.application.service.dto.GetTotalTransactionsAmountQuery;
import ir.ahs441.wallet.application.service.dto.GetTotalTransactionsAmountResponse;
import ir.ahs441.wallet.domain.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalTime;

@Log4j2
@Service
@RequiredArgsConstructor
@Validated
public class TransactionsApplicationService {

    private final TransactionsRepository transactionsRepository;


    public GetTotalTransactionsAmountResponse calculateTotalAmountOfTransactions(GetTotalTransactionsAmountQuery getTotalTransactionsAmountQuery) {
        var when = getTotalTransactionsAmountQuery.when();
        var totalAmountOfTransactions = transactionsRepository
                .calculateTotalAmountOfTransactions(when.atStartOfDay(), when.atTime(LocalTime.MAX));
//        var totalWithdrawTransactions = transactionsRepository.calculateTotalAmountOfTransactions(startDate,endDate, Transaction.TransactionType.WITHDRAW);
//        var totalDepositTransactions = totalAmountOfTransactions.subtract(totalWithdrawTransactions);
        return new GetTotalTransactionsAmountResponse(totalAmountOfTransactions == null ? BigDecimal.ZERO : totalAmountOfTransactions);
    }
}
