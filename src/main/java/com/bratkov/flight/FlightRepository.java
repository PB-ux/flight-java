package com.bratkov.flight;

import com.lab.flight.Flight;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

@Component
public class FlightRepository {
    private static Flight[] flights;

    @PostConstruct
    public void initData() throws DatatypeConfigurationException {
        flights = new Flight[2];

        Flight flightParis = new Flight();
        String dateValue = "2024-12-10"; // Дата в формате xs:date
        XMLGregorianCalendar departureDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateValue);
        flightParis.setFlightId(1);
        flightParis.setDepartureDate(departureDate);
        flightParis.setAvailableSeats(20);
        flightParis.setDeparturePoint("Paris");
        flights[0] = flightParis;

        Flight flightItaly = new Flight();
        String dateValue2 = "2024-12-11"; // Дата в формате xs:date
        XMLGregorianCalendar departureDate2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateValue);
        flightItaly.setFlightId(2);
        flightItaly.setDepartureDate(departureDate2);
        flightItaly.setAvailableSeats(0);
        flightItaly.setDeparturePoint("Italy");
        flights[1] = flightItaly;
    }

    public Flight[] getAvailableFlights(XMLGregorianCalendar departureDate, String departurePoint) {
        Assert.notNull(departureDate, "The departureDate must not be null");
        Assert.notNull(departurePoint, "The departurePoint must not be null");

        List<Flight> availableFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (departureDate.equals(flight.getDepartureDate()) && departurePoint.equalsIgnoreCase(flight.getDeparturePoint()) && flight.getAvailableSeats() > 0) {
                availableFlights.add(flight);
            }
        }

        return availableFlights.toArray(new Flight[0]);
    }
}
