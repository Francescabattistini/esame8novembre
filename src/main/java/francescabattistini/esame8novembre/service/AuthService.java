package francescabattistini.esame8novembre.service;

import francescabattistini.esame8novembre.Tools.Jwt;
import francescabattistini.esame8novembre.entities.User;
import francescabattistini.esame8novembre.exceptions.UnauthorizedException;
import francescabattistini.esame8novembre.payload.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private Jwt jwt;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UserLoginDto body) {
        User found = this.usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwt.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }

}
