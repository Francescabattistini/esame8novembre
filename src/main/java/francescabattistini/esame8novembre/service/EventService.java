package francescabattistini.esame8novembre.service;

import francescabattistini.esame8novembre.entities.Event;
import francescabattistini.esame8novembre.entities.User;
import francescabattistini.esame8novembre.exceptions.BadRequestException;
import francescabattistini.esame8novembre.exceptions.NotFoundException;
import francescabattistini.esame8novembre.payload.EventDto;
import francescabattistini.esame8novembre.respositories.EventRepository;
import francescabattistini.esame8novembre.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Event> findAll(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventRepository.findAll(pageable);
    }
    public Event findById(Long eventId) {
        return this.eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(String.valueOf(eventId)));
    }

    public Event save (EventDto body){
        if(body.dateEvent().isBefore(LocalDate.now())){
            throw new BadRequestException("mi dispiace ma la data dell'evento non può essere antecedente ad oggi !");
        }
        this.eventRepository.findByDateEvent(body.dateEvent()).ifPresent(
                evento -> {
                    throw new BadRequestException("Un evento con questa data esiste già!");
                }
        );
        User manager = userRepository.findById(Long.valueOf(body.idManager())).orElseThrow(() ->
                new BadRequestException("Manager non trovato con ID: " + body.idManager())
        );
        Event event = new Event(
                body.eventName(),
                body.descriptionEvent(),
                body.dateEvent(),
                body.locationEvento(),
                body.numPlaesAvabile(),
                manager
                );
        return this.eventRepository.save(event);
    }

    //ricordati di settarlo per gli organizzatori dell'evento
 public Event findByIdAndUpDate(Long eventId,EventDto body){
     Event event = this.findById(eventId);


     event.setEventName(body.eventName());
     event.setDescriptionEvent(body.descriptionEvent());
     event.setDateEvent(body.dateEvent());
     event.setLocationEvento(body.locationEvento());
     event.setNumPlaesAvabile(body.numPlaesAvabile());


     return this.eventRepository.save(event);
 }
 public void findByAndDelate(Long eventId){
        Event event = this.findById(eventId);
        this.eventRepository.delete(event);
 }


}
