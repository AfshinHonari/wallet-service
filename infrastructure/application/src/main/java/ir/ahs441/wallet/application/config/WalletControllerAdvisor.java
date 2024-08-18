package ir.ahs441.wallet.application.config;


import ir.ahs441.wallet.domain.exception.WalletServiceDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WalletControllerAdvisor {

    @ExceptionHandler({WalletServiceDomainException.class})
    public ResponseEntity<BareResponse> handleException(WalletServiceDomainException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BareResponse(400, ex.getMessage()));
    }

    public record BareResponse(int code, String message) {}
}