package app.services;

import app.dto.FlightSeatDTO;
import app.entities.Flight;
import app.entities.FlightSeat;
import app.entities.Seat;
import app.enums.CategoryType;
import app.mappers.FlightSeatMapper;
import app.repositories.FlightRepository;
import app.repositories.FlightSeatRepository;
import app.repositories.SeatRepository;
import app.services.interfaces.FlightSeatService;
import app.services.interfaces.FlightService;
import app.services.interfaces.SeatService;
import app.util.aop.Loggable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightSeatServiceImpl implements FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final SeatService seatService;
    private final FlightService flightService;

    @Override
    @Loggable
    public Set<FlightSeat> getAllFlightSeats() {
        Set<FlightSeat> flightSeatSet = new HashSet<>();
        flightSeatRepository.findAll().forEach(flightSeatSet::add);
        return flightSeatSet;
    }

    @Override
    @Transactional(readOnly = true)
    @Loggable
    public Page<FlightSeatDTO> getFreeSeatsById(Pageable pageable, Long id) {
        return flightSeatRepository.findFlightSeatByFlightIdAndIsSoldFalseAndIsRegisteredFalseAndIsBookedFalse(id, pageable)
                .map(entity -> FlightSeatMapper.INSTANCE.convertToFlightSeatDTOEntity(entity,flightService,seatService));
    }

    @Override
    public Page<FlightSeat> getAllFlightSeats(Integer page, Integer size) {
        return flightSeatRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<FlightSeat> getFlightSeatById(Long id) {
        return flightSeatRepository.findById(id);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    @Loggable
    public Set<FlightSeat> getFlightSeatsByFlightId(Long flightId) {
        return flightSeatRepository.findFlightSeatsByFlightId(flightId);
    }

    @Override
    public Page<FlightSeatDTO> getFlightSeatsByFlightId(Long flightId, Pageable pageable) {
        return flightSeatRepository.findFlightSeatsByFlightId(flightId, pageable)
                .map(entity -> FlightSeatMapper.INSTANCE.convertToFlightSeatDTOEntity(entity,flightService,seatService));
    }

    @Override
    public Set<FlightSeat> getFlightSeatsByFlightNumber(String flightNumber) {
        Set<FlightSeat> flightSeatByFlight = flightSeatRepository.findFlightSeatByFlight(flightRepository.getByCode(flightNumber));
        return new HashSet<>(flightSeatByFlight);
    }

    @Transactional
    @Override
    @Loggable
    public Set<FlightSeat> addFlightSeatsByFlightId(Long flightId) {
        Set<FlightSeat> newFlightSeats = new HashSet<>();
        var flight = flightService.getFlightById(flightId).get();
        var seats = seatRepository.findByAircraftId(flight.getAircraft().getId());
        for (Seat s : seats) {
            var flightSeat = new FlightSeat();
            flightSeat.setSeat(s);
            flightSeat.setFlight(flight);
            flightSeat.setIsBooked(false);
            flightSeat.setIsSold(false);
            flightSeat.setIsRegistered(false);
            flightSeat.setFare(generateFareForFlightseat(s));
            newFlightSeats.add(flightSeat);
        }
        for (FlightSeat f : newFlightSeats) {
            saveFlightSeat(f);
        }
        return newFlightSeats;
    }

    @Override
    @Transactional
    @Loggable
    public Set<FlightSeat> addFlightSeatsByFlightNumber(String flightNumber) {
        Set<FlightSeat> seatsForAdd = new HashSet<>();
        var allFlightSeats = getAllFlightSeats();
        var flight = flightRepository.getByCode(flightNumber);
        if (flight != null) {
           var seatsAircraft = flight.getAircraft().getSeatSet();

            for (Seat s : seatsAircraft) {
                var flightSeat = new FlightSeat();
                flightSeat.setSeat(s);
                flightSeat.setFlight(flight);
                if (allFlightSeats.contains(flightSeat)) {
                    continue;
                }
                flightSeat.setFare(0);
                flightSeat.setIsSold(false);
                flightSeat.setIsRegistered(false);
                seatsForAdd.add(flightSeat);
            }
            // todo коректнее сделать так
            //      flightSeatRepository.saveAll(seatsForAdd);
            for (FlightSeat f : seatsForAdd) {
                f = flightSeatRepository.save(f);
            }
        }
        return seatsForAdd;
    }

    @Override
    @Transactional
    @Loggable
    public FlightSeat saveFlightSeat(FlightSeat flightSeat) {
        return flightSeatRepository.save(flightSeat);
    }

    @Loggable
    public FlightSeat editFlightSeat(Long id, FlightSeatDTO flightSeatDTO) {
        var flightSeat = FlightSeatMapper.INSTANCE.convertToFlightSeatEntity(flightSeatDTO,flightService,seatService);
        var targetFlightSeat = flightSeatRepository.findById(id).orElse(null);
        flightSeat.setId(id);

        if (flightSeat.getFare() == null) {
            flightSeat.setFare(targetFlightSeat.getFare());
        }
        if (flightSeat.getIsSold() == null) {
            flightSeat.setIsSold(targetFlightSeat.getIsSold());
        }
        if (flightSeat.getIsBooked() == null) {
            flightSeat.setIsBooked(targetFlightSeat.getIsBooked());
        }
        if (flightSeat.getFlight() == null) {
            flightSeat.setFlight(targetFlightSeat.getFlight());
        }
        if (flightSeat.getSeat() == null) {
            flightSeat.setSeat(targetFlightSeat.getSeat());
        }
        return flightSeatRepository.save(flightSeat);
    }

    @Override
    @Loggable
    public int getNumberOfFreeSeatOnFlight(Flight flight) {
        return flight.getAircraft().getSeatSet().size() - flightSeatRepository.findFlightSeatByFlight(flight).size();
    }

    @Override
    @Loggable
    public Set<Seat> getSetOfFreeSeatsOnFlightByFlightId(Long id) {
        var targetFlight = flightRepository.getById(id);
        var setOfSeat = targetFlight.getAircraft().getSeatSet();
        var setOfReservedSeat = flightSeatRepository.findFlightSeatByFlight(targetFlight)
                .stream().map(FlightSeat::getSeat)
                .collect(Collectors.toSet());
        for (Seat s : setOfReservedSeat) {
            setOfSeat.remove(s);
        }
        return setOfSeat;
    }

    @Override
    @Loggable
    public Set<FlightSeat> getFlightSeatsBySeat(Seat seat) {
        return flightSeatRepository.findFlightSeatsBySeat(seat);
    }

    @Override
    @Loggable
    public void deleteFlightSeatById(Long id) {
        flightSeatRepository.deleteById(id);
    }

    @Override
    @Loggable
    public Set<FlightSeat> getNotSoldFlightSeatsById(Long id) {
        return flightSeatRepository.findAllFlightsSeatByFlightIdAndIsSoldFalse(id);
    }

    @Override
    @Loggable
    public Page<FlightSeatDTO> findNotRegisteredFlightSeatsById(Long id, Pageable pageable) {
        return flightSeatRepository.findAllFlightsSeatByFlightIdAndIsRegisteredFalse(id, pageable)
                .map(entity -> FlightSeatMapper.INSTANCE.convertToFlightSeatDTOEntity(entity,flightService,seatService));
    }

    @Override
    public List<FlightSeat> getCheapestFlightSeatsByFlightIdAndSeatCategory(Long id, CategoryType type) {
        return flightSeatRepository.findFlightSeatsByFlightIdAndSeatCategory(id, type);
    }


    public Page<FlightSeatDTO> getNotSoldFlightSeatsById(Long id, Pageable pageable) {
        return flightSeatRepository.findAllFlightsSeatByFlightIdAndIsSoldFalse(id, pageable)
                .map(entity -> FlightSeatMapper.INSTANCE.convertToFlightSeatDTOEntity(entity,flightService,seatService));
    }

    @Override
    @Transactional
    public void editFlightSeatIsSoldToFalseByFlightSeatId(long[] flightSeatId) {
        flightSeatRepository.editIsSoldToFalseByFlightSeatId(flightSeatId);
    }

    @Override
    public List<FlightSeat> findByFlightId(Long id) {
        return flightSeatRepository.findByFlightId(id);
    }

    public int generateFareForFlightseat(Seat seat) {
        int baseFare = 5000;
        float emergencyExitRatio;
        float categoryRatio;
        float lockedBackRatio;
        if (seat.getIsNearEmergencyExit()) {
            emergencyExitRatio = 1.3f;
        } else emergencyExitRatio = 1f;
        if (seat.getIsLockedBack()) {
            lockedBackRatio = 0.8f;
        } else lockedBackRatio = 1f;
        switch (seat.getCategory().getCategoryType()) {
            case PREMIUM_ECONOMY : categoryRatio = 1.2f;
                break;
            case BUSINESS : categoryRatio = 2f;
                break;
            case FIRST : categoryRatio = 2.5f;
                break;
            default : categoryRatio = 1f;
        }
        return Math.round(baseFare * emergencyExitRatio * categoryRatio * lockedBackRatio);
    }
}
