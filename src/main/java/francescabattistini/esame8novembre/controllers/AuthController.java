package francescabattistini.esame8novembre.controllers;

import francescabattistini.esame8novembre.entities.User;
import francescabattistini.esame8novembre.exceptions.BadRequestException;
import francescabattistini.esame8novembre.payload.UserDto;
import francescabattistini.esame8novembre.payload.UserLoginDto;
import francescabattistini.esame8novembre.payload.UserLoginResponseDto;
import francescabattistini.esame8novembre.service.AuthService;
import francescabattistini.esame8novembre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService usersService;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginDto body) {
        return new UserLoginResponseDto(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated UserDto body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.usersService.save(body);
    }
}
