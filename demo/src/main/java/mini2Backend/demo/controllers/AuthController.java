package mini2Backend.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mini2Backend.demo.DTO.LoginRequest;
import mini2Backend.demo.DTO.LoginResponse;
import mini2Backend.demo.DTO.RegisterRequest;
import mini2Backend.demo.DTO.RegisterResponse;
import mini2Backend.demo.service.UserCommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@Tag(name="Authorization")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserCommonService userCommonService;

    @Operation(
            description = "End point to register a user",
            summary="A user can register by providing the required details"
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(userCommonService.register(registerRequest));
    }

    @Operation(
            description = "End point to login",
            summary="If the user is registered and tries to login with Valid Username and password, token and refreshToken is generated"
    )
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return userCommonService.login(loginRequest);
    }


}
