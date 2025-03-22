package ru.example.losingweighttogether.meal.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.example.losingweighttogether.user.model.User;

import java.time.LocalDate;

@Entity
@Table(name = "meal_records")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "eaten_kkal", nullable = false)
    Double eatenKkal;

    @Column(name = "meal_date", nullable = false)
    LocalDate mealDate;
}
