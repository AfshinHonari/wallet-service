package ir.ahs441.wallet.application.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GetBalanceResponse(@NotNull BigDecimal balance) {
}
