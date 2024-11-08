package francescabattistini.esame8novembre.respositories;

import francescabattistini.esame8novembre.entities.Booking;
import francescabattistini.esame8novembre.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findByUserAndDataEvent(User user, LocalDate dateEvent);

}
