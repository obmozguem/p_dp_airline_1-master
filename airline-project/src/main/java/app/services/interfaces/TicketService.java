package app.services.interfaces;

import app.dto.TicketDTO;
import app.entities.Ticket;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketService {

    Page<Ticket> getAllTickets(int page, int size);

    Ticket getTicketByTicketNumber(String bookingNumber);

    void deleteTicketById(Long id);

    Ticket saveTicket(TicketDTO ticketDTO);

    Ticket updateTicketById(Long id, TicketDTO ticketDTO);


    long [] getArrayOfFlightSeatIdByPassengerId(long passengerId);

    void deleteTicketByPassengerId(long passengerId);

    List<Ticket> findByFlightId(Long id);
}
