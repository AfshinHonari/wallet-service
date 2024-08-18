package ir.ahs441.wallet.application.rest;

import ir.ahs441.wallet.application.rest.dto.AuthenticationRequest;
import ir.ahs441.wallet.application.rest.dto.AuthenticationResponse;
import ir.ahs441.wallet.application.serivce.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        var result = userDetailsService.authenticate(request.username(),request.password());
        return ResponseEntity.ok((new AuthenticationResponse(result.token())));
    }
}
