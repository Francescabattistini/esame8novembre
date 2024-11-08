package francescabattistini.esame8novembre.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String eventName;
    private String descriptionEvent;
    private LocalDate dateEvent;
    private String locationEvento;
    private int numPlaesAvabile;
    @ManyToOne
    @JoinColumn(name= "id_manager")
    private User Manager;// riferimento al creatore stesso

    public Event(String eventName, String descriptionEvent, LocalDate dateEvent,
                 String locationEvento, int numPlaesAvabile, User manager) {
        this.eventName = eventName;
        this.descriptionEvent = descriptionEvent;
        this.dateEvent = dateEvent;
        this.locationEvento = locationEvento;
        this.numPlaesAvabile = numPlaesAvabile;
        Manager = manager;
    }
}

