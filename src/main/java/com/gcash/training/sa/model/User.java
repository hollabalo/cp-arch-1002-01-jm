package com.gcash.training.sa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author johnmichael.gerona
 * @created 12/14/23
 */

@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private final UUID userId;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String email;
    private final Gender gender;

    public enum Gender {
        Male,
        Female
    }

    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + "'" +
                ", lastName='" + lastName + "'" +
                ", gender='" + gender + "'" +
                ", age='" + age + "'" +
                ", email='" + email + "'" +
                "}";
    }
}
