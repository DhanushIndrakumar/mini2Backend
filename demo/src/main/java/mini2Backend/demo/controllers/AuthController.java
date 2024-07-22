package mini2Backend.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mini2Backend.demo.DTO.*;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.UserRepository;
import mini2Backend.demo.service.JWTService;
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

    private final JWTService jwtService;

    private final UserRepository userRepository;

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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userCommonService.login(loginRequest));
    }
    @PostMapping("/getDetailsByToken")
    public RegisterResponse getDetails(@RequestBody DetailsToken detailsToken) {
        String token = detailsToken.getToken();
        String userEmail = jwtService.extractUserName(token);
        System.out.println(userEmail);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("user:" + user.getUserId());
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserId(user.getUserId());
        registerResponse.setUserName(user.getUserName());
        registerResponse.setEmail(user.getEmail());
        registerResponse.setPhone(user.getPhone());
        registerResponse.setMedicalHistory(user.getMedicalHistory());
        registerResponse.setRole(user.getRole());
        return registerResponse;
    }
}


