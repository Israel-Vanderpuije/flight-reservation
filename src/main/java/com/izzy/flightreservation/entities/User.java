package com.izzy.flightreservation.entities;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity{

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
