package app.dto;

import app.entities.Ticket;
import app.enums.Airport;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TicketDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank(message = "Field should not be empty")
    private String ticketNumber;
    @NotNull
    private Long passengerId;
    @NotBlank(message = "Field should not be empty")
    @Size(min = 2, max = 128, message = "Firstname size cannot be less than 2 and more than 128 characters")
    private String firstName;
    @NotBlank(message = "Field should not be empty")
    @Size(min = 2, max = 128, message = "Lastname size cannot be less than 2 and more than 128 characters")
    private String lastName;
    @NotNull
    private Long flightId;
    @NotBlank(message = "Flight code cannot be empty")
    @Size(min = 2, max = 15, message = "Length of flight code should be between 2 and 15 characters")
    private String code;
    @NotNull
    private Airport from;
    @NotNull
    private Airport to;
    @NotNull
    private LocalDateTime departureDateTime;
    @NotNull
    private LocalDateTime arrivalDateTime;
    @NotNull
    private Long flightSeatId;
    @NotBlank(message = "Field seat number cannot be null")
    @Size(min = 2, max = 5, message = "Seat number must be between 2 and 5 characters")
    private String seatNumber;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.ticketNumber = ticket.getTicketNumber();
        this.passengerId = ticket.getPassenger().getId();
        this.firstName = ticket.getPassenger().getFirstName();
        this.lastName = ticket.getPassenger().getLastName();
        this.flightId = ticket.getFlight().getId();
        this.code = ticket.getFlight().getCode();
        this.from = ticket.getFlight().getFrom().getAirportCode();
        this.to = ticket.getFlight().getTo().getAirportCode();
        this.departureDateTime = ticket.getFlight().getDepartureDateTime();
        this.arrivalDateTime = ticket.getFlight().getArrivalDateTime();
        this.flightSeatId = ticket.getFlightSeat().getId();
        this.seatNumber = ticket.getFlightSeat().getSeat().getSeatNumber();
    }
}