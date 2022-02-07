package com.izzy.flightreservation.controllers;

import com.izzy.flightreservation.dto.ReservationRequest;
import com.izzy.flightreservation.dto.ReservationUpdateRequest;
import com.izzy.flightreservation.entities.Reservation;
import com.izzy.flightreservation.repositories.FlightRepository;
import com.izzy.flightreservation.repositories.ReservationRepository;
import com.izzy.flightreservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){
        flightRepository.findById(flightId).ifPresent(location -> modelMap.addAttribute("flight", location));
        return "completeReservation";
    }

    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap){
        Reservation reservation = reservationService.bookFlight(reservationRequest);
        modelMap.addAttribute("msg", "Reservation created successfully. ID is: " + reservation.getId());
        return "reservationConfirmation";
    }

    //Using @ResponseBody 'cos we're making it a Rest endpoint. Request response
    //will be an object in json format and not a html template like the others.
    @RequestMapping("/reservation/{id}")
    public @ResponseBody Optional<Reservation> findReservation(@PathVariable("id") Long id){
        return reservationRepository.findById(id);
    }

    @RequestMapping("/updateReservation")
    public @ResponseBody Optional<Reservation> updateReservation(@RequestBody ReservationUpdateRequest updateRequest){
        Optional<Reservation> reservation = reservationRepository.findById(updateRequest.getId());
        reservation.ifPresent(x -> {x.setNumberOfBags(updateRequest.getNumberOfBags());
                                    x.setCheckedIn(updateRequest.getCheckedIn());
                                    reservationRepository.save(x);});
       return reservation;
    }

}
