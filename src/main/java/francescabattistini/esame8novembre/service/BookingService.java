package francescabattistini.esame8novembre.service;

import francescabattistini.esame8novembre.entities.Booking;
import francescabattistini.esame8novembre.entities.Event;
import francescabattistini.esame8novembre.entities.User;
import francescabattistini.esame8novembre.exceptions.NotFoundException;
import francescabattistini.esame8novembre.payload.BookingDto;
import francescabattistini.esame8novembre.respositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    public Page<Booking> findAll(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookingRepository.findAll(pageable);
    }
    public Booking findById(Long bookingId) {
        return this.bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException(String.valueOf((bookingId))));
    }

    public Optional<Booking> findForUserAndData(Long userid, LocalDate dateEvent){
        User user = this.userService.findById(userid);
        return this.bookingRepository.findByUserAndDataEvent(user,dateEvent);

    }
    public Booking save(BookingDto body){
        // 1.trovare id evento
        //2. trovare id user
        //3. creare la prenotazione
        //4. salvarla
            Long eventId = body.idEvent();
            Event evento = eventService.findById(eventId);

            Long userId = body.idUser();
            User utente = userService.findById(userId);

            Booking booking = new Booking(utente, evento);

            return bookingRepository.save(booking);
        }

    }


    public Booking bookingupDate(){}

    public void findAndDelate(){}

}
