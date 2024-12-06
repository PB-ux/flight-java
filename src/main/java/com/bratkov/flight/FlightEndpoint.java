package com.bratkov.flight;

import com.lab.flight.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FlightEndpoint {
    private static final String NAMESPACE_URI = "http://lab.com/flight";

    private FlightRepository flightRepository;

    @Autowired
    public FlightEndpoint(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createFlightRequest")
    @ResponsePayload
    public CreateFlightResponse createFlight(@RequestPayload CreateFlightRequest request) {
        CreateFlightResponse response = new CreateFlightResponse();
        Flight newFlight = flightRepository.create(request.getFlightId(), request.getDepartureDate(), request.getAvailableSeats());
        response.setFlight(newFlight);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "checkFlightAvailable")
    @ResponsePayload
    public CheckFlightAvailabilityResponse checkFlightAvailable(@RequestPayload CheckFlightAvailabilityRequest request) {
        CheckFlightAvailabilityResponse response = new CheckFlightAvailabilityResponse();
        String status = flightRepository.checkFlightAvailable(request.getFlights(), request.getFlightId(), request.getDepartureDate());

        response.setStatus(status);

        return response;
    }
}
