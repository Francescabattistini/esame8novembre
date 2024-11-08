package francescabattistini.esame8novembre.respositories;

import francescabattistini.esame8novembre.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
Optional<Event> findByDateEvent(LocalDate dateEvent);
}
