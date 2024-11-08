package francescabattistini.esame8novembre.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name="id_event")
    private Event event;

    public Booking(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
