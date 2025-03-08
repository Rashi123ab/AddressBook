package com.rashi.AddressBook.Address.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
}
