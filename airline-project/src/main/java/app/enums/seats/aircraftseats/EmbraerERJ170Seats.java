package app.enums.seats.aircraftseats;

import app.enums.seats.interfaces.AircraftSeats;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmbraerERJ170Seats implements AircraftSeats {

    C1("C1", false, false),
    D1("D1", false, false),

    A2("A2", false, false),
    B2("B2", false, false),

    A3("A3", false, false),
    B3("B3", false, false),
    C3("C3", false, false),
    D3("D3", false, false),

    A4("A4", false, false),
    B4("B4", false, false),
    C4("C4", false, false),
    D4("D4", false, false),

    A5("A5", false, false),
    B5("B5", false, false),
    C5("C5", false, false),
    D5("D5", false, false),

    A6("A6", false, false),
    B6("B6", false, false),
    C6("C6", false, false),
    D6("D6", false, false),

    A7("A7", false, false),
    B7("B7", false, false),
    C7("C7", false, false),
    D7("D7", false, false),

    A8("A8", false, false),
    B8("B8", false, false),
    C8("C8", false, false),
    D8("D8", false, false),

    A9("A9", false, false),
    B9("B9", false, false),
    C9("C9", false, false),
    D9("D9", false, false),

    A10("A10", false, false),
    B10("B10", false, false),
    C10("C10", false, false),
    D10("D10", false, false),

    A11("A11", false, false),
    B11("B11", false, false),
    C11("C11", false, false),
    D11("D11", false, false),

    A12("A12", false, false),
    B12("B12", false, false),
    C12("C12", false, false),
    D12("D12", false, false),

    A13("A13", false, false),
    B13("B13", false, false),
    C13("C13", false, false),
    D13("D13", false, false),

    A14("A14", false, false),
    B14("B14", false, false),
    C14("C14", false, false),
    D14("D14", false, false),

    A15("A15", false, false),
    B15("B15", false, false),
    C15("C15", false, false),
    D15("D15", false, false),

    A16("A16", false, false),
    B16("B16", false, false),
    C16("C16", false, false),
    D16("D16", false, false),

    A17("A17", false, false),
    B17("B17", false, false),
    C17("C17", false, false),
    D17("D17", false, false),

    A18("A18", false, false),
    B18("B18", false, false),
    C18("C18", false, false),
    D18("D18", false, false),

    A19("A19", false, false),
    B19("B19", false, false),
    C19("C19", false, false),
    D19("D19", false, false),

    A20("A20", false, true),
    B20("B20", false, true),
    C20("C20", false, true),
    D20("D20", false, true);

    private final String number;
    private final boolean isNearEmergencyExit;
    private final boolean isLockedBack;
}
