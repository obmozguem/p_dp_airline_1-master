package app.controllers.rest;

import app.controllers.api.rest.BookingRestApi;
import app.dto.BookingDTO;
import app.entities.Booking;
import app.enums.BookingStatus;
import app.mappers.BookingMapper;
import app.services.interfaces.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BookingRestController implements BookingRestApi {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @Override
    public ResponseEntity<Page<BookingDTO>> getAllPagesBookingsDTO(Integer page, Integer size) {
        log.info("getAll: search all Bookings");
        Page<BookingDTO> bookings = bookingService.getAllBookings(page, size);
        if (bookings == null) {
            log.info("getAll: Bookings not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingDTO> getBookingDTOById(Long id) {
        log.info("getById: search Booking by id = {}", id);
        var booking = bookingService.getBookingById(id);
        if (booking == null) {
            log.info("getById: not found Booking with id = {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookingMapper.convertToBookingDTOEntity(booking), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingDTO> getBookingDTOByBookingNumber(String bookingNumber) {
        log.info("getByNumber: search Booking by number = {}", bookingNumber);
        var booking = bookingService.getBookingByNumber(bookingNumber);
        if (booking == null) {
            log.info("getByNumber: not found Booking with number = {}", bookingNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookingMapper.convertToBookingDTOEntity(booking), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookingDTO> createBookingDTO(BookingDTO bookingDTO) {
        log.info("create: creating a new Booking");
        bookingDTO.setBookingStatus(BookingStatus.NOT_PAID);
        return new ResponseEntity<>(bookingMapper.convertToBookingDTOEntity(
                bookingService.saveBooking(bookingDTO)),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookingDTO> updateBookingDTOById(Long id, BookingDTO bookingDTO) {
        log.info("update: edit Booking with id = {}", id);
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            log.info("update: not found Booking with id = {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookingDTO.setBookingStatus(booking.getBookingStatus());
        bookingDTO.setId(id);
        return new ResponseEntity<>(bookingMapper.convertToBookingDTOEntity(
                bookingService.saveBooking(bookingDTO)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteBookingById(Long id) {
        log.info("deleteAircraftById: deleting a Booking with id = {}", id);
        try {
            bookingService.deleteBookingById(id);
        } catch (Exception e) {
            log.error("deleteAircraftById: error of deleting - Booking with id = {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}