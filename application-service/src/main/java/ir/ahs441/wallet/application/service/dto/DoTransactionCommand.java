package ir.ahs441.wallet.application.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DoTransactionCommand(
        @NotNull @JsonProperty("userId") Long userId,
        @NotNull BigDecimal amount
) {
}
