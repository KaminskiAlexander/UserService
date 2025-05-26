package org.Kaminski.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "created_at")
    private LocalDateTime created_at = LocalDateTime.now();


    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.created_at = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Пользователь " + id +
                " - Был создан: " + created_at +
                " Возраст: " + age +
                ", email: " + email +
                ", Имя: " + name;
    }
}
