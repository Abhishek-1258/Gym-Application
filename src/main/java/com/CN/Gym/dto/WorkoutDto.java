package com.CN.Gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutDto {

       private String workoutName;
       private String description;
       private String difficultyLevel;
       private int duration;
}
