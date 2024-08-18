package ir.ahs441.wallet.application.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GetTotalTransactionsAmountQuery(@NotNull LocalDate when) {
}
