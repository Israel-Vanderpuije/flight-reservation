package com.izzy.flightreservation.services;

import com.izzy.flightreservation.dto.ReservationRequest;
import com.izzy.flightreservation.entities.Flight;
import com.izzy.flightreservation.entities.Passenger;
import com.izzy.flightreservation.entities.Reservation;
import com.izzy.flightreservation.repositories.FlightRepository;
import com.izzy.flightreservation.repositories.PassengerRepository;
import com.izzy.flightreservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation bookFlight(ReservationRequest reservationRequest){
        Long flightId = reservationRequest.getFlightId();
        Optional<Flight> flight = flightRepository.findById(flightId);

        Passenger passenger = new Passenger();
        passenger.setFirstname(reservationRequest.getPassengerFirstName());
        passenger.setLastname(reservationRequest.getPassengerLastName());
        passenger.setPhone(reservationRequest.getPassengerPhone());
        passenger.setEmail(reservationRequest.getPassengerEmail());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        flight.ifPresent(reservation::setFlight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false);
        return  reservationRepository.save(reservation);
    }
}
