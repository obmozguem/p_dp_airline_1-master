package app.mappers;

import app.dto.FlightDTO;
import app.entities.Flight;
import app.services.interfaces.*;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FlightMapper {
    @Mapping(target = "seats", expression = "java(flightSeatService.findByFlightId(flightDTO.getId()))")
    @Mapping(target = "ticket", expression = "java(ticketService.findByFlightId(flightDTO.getId()))")
    @Mapping(target = "from", expression = "java(destinationService.getDestinationByAirportCode(flightDTO.getAirportFrom()))")
    @Mapping(target = "to", expression = "java(destinationService.getDestinationByAirportCode(flightDTO.getAirportTo()))")
    @Mapping(target = "aircraft", expression = "java(aircraftService.getAircraftById(flightDTO.getAircraftId()))")
    Flight flightDTOtoFlight(FlightDTO flightDTO, @Context AircraftService aircraftService,
                             @Context DestinationService destinationService, @Context TicketService ticketService,
                             @Context FlightSeatService flightSeatService);

    @Mapping(target = "airportFrom", expression = "java(flight.getFrom().getAirportCode())")
    @Mapping(target = "airportTo", expression = "java(flight.getTo().getAirportCode())")
    @Mapping(target = "aircraftId", expression = "java(flight.getAircraft().getId())")
    FlightDTO flightToFlightDTO(Flight flight);
}
