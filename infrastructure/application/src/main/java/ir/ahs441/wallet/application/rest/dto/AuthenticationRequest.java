package ir.ahs441.wallet.application.rest.dto;

public record AuthenticationRequest(
        String username,
        String password
) {
}
