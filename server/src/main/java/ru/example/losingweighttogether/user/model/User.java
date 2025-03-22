package ru.example.losingweighttogether.user.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.example.losingweighttogether.user.enums.UserGender;
import ru.example.losingweighttogether.user.enums.UserGoals;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Enumerated(EnumType.STRING)
    UserGender gender;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    Long age;

    @Column(name = "weight", nullable = false)
    Long weightInKg;

    @Column(name = "height", nullable = false)
    Long heightInCm;

    @Enumerated(EnumType.STRING)
    UserGoals goal;
}
