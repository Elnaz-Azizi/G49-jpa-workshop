package se.lexicon.g49jpaworkshop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="details")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false)
    private String name;

    @Column(name = "birth_day")
    private LocalDate birthDay;


}
