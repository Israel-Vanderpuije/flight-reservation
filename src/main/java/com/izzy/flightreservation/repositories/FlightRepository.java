package com.izzy.flightreservation.repositories;

import com.izzy.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.sql.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM Flight WHERE departure_city = :departureCity " +
            "AND arrival_city = :arrivalCity " +
            "AND date_of_departure = :dateOfDeparture", nativeQuery = true)
    List<Flight> findFlights(@Param("departureCity") String from,
                             @Param("arrivalCity") String to,
                             @Param("dateOfDeparture") Date departureDate);
}
