package ir.ahs441.wallet.application.service.dto;

import jakarta.validation.constraints.NotNull;

public record GetBalanceQuery(@NotNull Long userId) {
}
