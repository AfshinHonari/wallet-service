package ir.ahs441.wallet.domain.valueobject;

import ir.ahs441.common.domain.valueobject.BaseId;

public class TransactionId extends BaseId<Long> {
    public TransactionId(Long value) {
        super(value);
    }
}
