package com.bratkov.flight;

import com.lab.flight.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Arrays;

@Endpoint
public class FlightEndpoint {
    private static final String NAMESPACE_URI = "http://lab.com/flight";

    private FlightRepository flightRepository;

    @Autowired
    public FlightEndpoint(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAvailableFlightsRequest")
    @ResponsePayload
    public GetAvailableFlightsResponse getAvailableFlights(@RequestPayload GetAvailableFlightsRequest request) {
        GetAvailableFlightsResponse response = new GetAvailableFlightsResponse();
        Flight[] flights = flightRepository.getAvailableFlights(request.getDepartureDate(), request.getDeparturePoint());
        response.getFlight().addAll(Arrays.asList(flights));

        return response;
    }
}
