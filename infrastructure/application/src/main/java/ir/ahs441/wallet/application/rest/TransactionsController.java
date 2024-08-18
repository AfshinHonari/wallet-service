package ir.ahs441.wallet.application.rest;

import ir.ahs441.wallet.application.service.TransactionsApplicationService;
import ir.ahs441.wallet.application.service.dto.GetTotalTransactionsAmountQuery;
import ir.ahs441.wallet.application.service.dto.GetTotalTransactionsAmountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsApplicationService transactionsApplicationService;

    @GetMapping("/today-total")
    public ResponseEntity<GetTotalTransactionsAmountResponse> getTotalTodayTransactions() {
        return ResponseEntity.ok(transactionsApplicationService.
                calculateTotalAmountOfTransactions(new GetTotalTransactionsAmountQuery(LocalDate.now()))
                );
    }


}
