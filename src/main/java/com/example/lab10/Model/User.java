package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "name cannot be null")
    @Size(min = 5,message = "name must be more than 4 character")
    @Column(columnDefinition = "varchar(10) not null")

    private String name;
    @Email
    @NotEmpty(message = "email cannot be empty")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    @Column(columnDefinition = "varchar(9) not null")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String password;
    @NotNull(message = "age cannot be null")
    @Min(value = 22)
    @Positive
    @Column(columnDefinition = "int not null")
     private int age;
    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "JOB_SEEKER|EMPLOYER", message = "role must be either 'JOB_SEEKER' or 'EMPLOYER' only")
@Column(columnDefinition = "varchar(11) not null ")
    //check(role='JOB_SEEKER' or role ='EMPLOYER')
    private String role;
}
