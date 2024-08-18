package ir.ahs441.wallet.application.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DoTransactionResponse(@NotNull @JsonProperty("referenceId") Long referenceId) {
}
