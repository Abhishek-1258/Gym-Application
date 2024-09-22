package com.CN.Gym.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Workout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workoutName;
    private String description;
    private String difficultyLevel;
    private int duration;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

}
