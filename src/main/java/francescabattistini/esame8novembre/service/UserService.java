package francescabattistini.esame8novembre.service;

import francescabattistini.esame8novembre.entities.User;
import francescabattistini.esame8novembre.exceptions.BadRequestException;
import francescabattistini.esame8novembre.exceptions.NotFoundException;
import francescabattistini.esame8novembre.payload.UserDto;
import francescabattistini.esame8novembre.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public Page<User> findAll(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }
    public User findById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("Utente non trovato con ID: " + userId));
    }


    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(()
                -> new NotFoundException("Mi dispiace ma l'utente con l'email, " + email + " sembra non essere presente"));
    }

    public User save(UserDto body) {
        this.userRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );
        User user = new User(
                body.name(),
                body.email(),
                body.password(),
                body.ruolo());
        return this.userRepository.save(user);
    }
    public User findByIdAndUpdate(Long userId, UserDto body) {
        User found = this.findById(userId);
        if (!found.getEmail().equals(body.email())) {
            this.userRepository.findByEmail(body.email()).ifPresent(
                    user -> {
                        throw new BadRequestException("mi dispiace email " + body.email() + " già in uso!");
                    }
            );
        }
        found.setName(body.name());
        found.setEmail(body.email());
        found.setPassword(body.password());

        return this.userRepository.save(found);
    }
    public void findByIdAndDelete(Long userId) {
        User found = this.findById(userId);
        this.userRepository.delete(found);
    }



}
