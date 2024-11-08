package francescabattistini.esame8novembre.service;

import francescabattistini.esame8novembre.entities.User;
import francescabattistini.esame8novembre.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }
    public User findById( long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }
}
