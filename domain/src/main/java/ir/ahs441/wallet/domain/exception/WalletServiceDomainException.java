package ir.ahs441.wallet.domain.exception;

import ir.ahs441.common.domain.exception.DomainException;

public class WalletServiceDomainException extends DomainException {
    public WalletServiceDomainException(String message) {
        super(message);
    }

    public WalletServiceDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
