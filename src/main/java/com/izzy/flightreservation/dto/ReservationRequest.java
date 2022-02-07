package com.izzy.flightreservation.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ReservationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;
    private String passengerFirstName;
    private String passengerLastName;
    private String passengerEmail;
    private String passengerPhone;
    private String nameOnCard;
    private String CardNumber;
    private String expirationCode;
    private String securityCode;
}
