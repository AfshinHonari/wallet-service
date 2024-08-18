package ir.ahs441.wallet.application.rest;

import ir.ahs441.wallet.application.service.WalletApplicationService;
import ir.ahs441.wallet.application.service.dto.DoTransactionCommand;
import ir.ahs441.wallet.application.service.dto.DoTransactionResponse;
import ir.ahs441.wallet.application.service.dto.GetBalanceQuery;
import ir.ahs441.wallet.application.service.dto.GetBalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletApplicationService walletApplicationService;

    @GetMapping("/balance/{userId}")
    public ResponseEntity<GetBalanceResponse> getBalance(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(walletApplicationService.getUserBalance(new GetBalanceQuery(userId)));
    }

    @PostMapping("/transactions")
    public ResponseEntity<DoTransactionResponse> doTransaction(@RequestBody DoTransactionCommand command) {
        return ResponseEntity.ok(walletApplicationService.doTransaction(command));
    }

}
