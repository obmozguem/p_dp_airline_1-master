package app.services.interfaces;

import app.dto.FlightSeatDTO;
import app.entities.Flight;
import app.entities.FlightSeat;
import app.entities.Seat;
import app.enums.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FlightSeatService {

    Set<FlightSeat> getAllFlightSeats();

    Page<FlightSeat> getAllFlightSeats(Integer page, Integer size);

    Optional<FlightSeat> getFlightSeatById(Long id);

    Set<FlightSeat> getFlightSeatsByFlightId(Long flightId);
    Page<FlightSeatDTO> getFreeSeatsById(Pageable pageable, Long id);
    Page<FlightSeatDTO> getFlightSeatsByFlightId(Long flightId, Pageable pageable);
    Set<FlightSeat> getFlightSeatsByFlightNumber(String flightNumber);

    Set<FlightSeat> addFlightSeatsByFlightId(Long flightId);

    Set<FlightSeat> addFlightSeatsByFlightNumber(String flightNumber);

    FlightSeat saveFlightSeat(FlightSeat flightSeat);
    int getNumberOfFreeSeatOnFlight(Flight flight);
    Set<Seat> getSetOfFreeSeatsOnFlightByFlightId(Long id);
    Set<FlightSeat> getFlightSeatsBySeat(Seat seat);

    void deleteFlightSeatById(Long id);

    Set<FlightSeat> getNotSoldFlightSeatsById(Long id);

    List<FlightSeat> getCheapestFlightSeatsByFlightIdAndSeatCategory(Long id, CategoryType type);

    Page<FlightSeatDTO> getNotSoldFlightSeatsById(Long id, Pageable pageable);
    Page<FlightSeatDTO> findNotRegisteredFlightSeatsById(Long id, Pageable pageable);

    void editFlightSeatIsSoldToFalseByFlightSeatId(long[] flightSeatId);

    List<FlightSeat> findByFlightId (Long id);

}
