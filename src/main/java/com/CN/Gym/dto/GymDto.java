package com.CN.Gym.dto;

import java.util.ArrayList;
import java.util.List;

import com.CN.Gym.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GymDto {

    
       private String name;
       private String address;
       private Long contactNo;
       private String membershipPlans;
       private String facilities;
       private List<User> members = new ArrayList<>();
    

}
