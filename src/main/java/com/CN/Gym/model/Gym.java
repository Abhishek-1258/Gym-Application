package com.CN.Gym.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gym {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Long id;
	
	@Column
    private String name;
	
	@Column
    private String address;
	@Column
    private Long contactNo;
	@Column
    private String membershipPlans;
	@Column
    private String facilities;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "gym",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<User> members = new ArrayList<User>();

}
