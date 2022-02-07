package com.izzy.flightreservation.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends AbstractEntity{

    private String firstname;
    private String lastname;
    private String email;
    private String phone;

}
