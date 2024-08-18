package ir.ahs441.wallet.application.job;

import ir.ahs441.wallet.application.service.TransactionsApplicationService;
import ir.ahs441.wallet.application.service.dto.GetTotalTransactionsAmountQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Log4j2
public class TransactionsJob {

    TransactionsApplicationService transactionsApplicationService;

    @Scheduled(cron = "59 59 23 * * *")
    @Profile("cron")
    public void calculateDailyTransactions() {
        var now = LocalDate.now();
        var totalAmount = transactionsApplicationService
                .calculateTotalAmountOfTransactions(new GetTotalTransactionsAmountQuery(now));
        var transactionsAmount = String.format("Total Amount of: %s Transactions is: %s ", now, totalAmount);
        System.out.println(transactionsAmount);
        log.info(transactionsAmount);
    }

}
