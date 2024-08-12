package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.Mac;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "title cannot be empty")
    @Size(min = 5,message = "title must be more than 4 character")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;
    @NotEmpty(message = "description cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")

    private String description;
    @NotEmpty(message = "location cannot be empty")
    @Column(columnDefinition = " varchar(100) not null")
    private String location;
    @NotNull(message = "salary cannot be null")
    @Column(columnDefinition = " int not null")
    @Positive
    private int salary;
    @NotNull(message = "date cannot be null")
    @Column(columnDefinition = " Date not null")
    private Date postingDate;
}
