package com.bratkov.flight;

import com.lab.flight.Flight;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;
import java.util.Optional;

@Component
public class FlightRepository {
    public Flight create(Integer flightId, XMLGregorianCalendar departureDate, Integer availableSeats) {
        Assert.notNull(flightId, "The flight id must not be null");
        Assert.notNull(departureDate, "The departure date must not be null");
        Assert.notNull(availableSeats, "The available seats must not be null");

        Flight newFlight = new Flight();
        newFlight.setFlightId(flightId);
        newFlight.setDepartureDate(departureDate);
        newFlight.setAvailableSeats(availableSeats);

        return newFlight;
    };

    public String checkFlightAvailable(List<Flight> flights, Integer flightId, XMLGregorianCalendar departureDate) {
        Optional<Flight> flight = flights.stream()
                .filter(f -> f.getFlightId() == flightId && f.getDepartureDate().equals(departureDate))
                .findFirst();

        return flight.isPresent() ? "Flight is available" : "Flight is not available";
    }

}
