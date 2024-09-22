package com.CN.Gym.service;


import com.CN.Gym.dto.GymDto;
import com.CN.Gym.exception.GymNotFoundException;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Gym;
import com.CN.Gym.model.User;
import com.CN.Gym.repository.GymRepository;
import com.CN.Gym.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymService {

    @Autowired
    GymRepository gymRepository;
    
    @Autowired
    UserRepository userRepository;


    public List<Gym> getAllGyms() {   
    	return gymRepository.findAll();
    }

     public Gym getGymById(Long id) {
    	 Optional<Gym> gymDetails = gymRepository.findById(id);
    	 Gym gym = gymDetails.orElseThrow(() -> new GymNotFoundException("Gym Not Found"));
    	 
    	 return gym;
    }

    public void deleteGymById(Long id) {        
    	Optional<Gym> gymDetails = gymRepository.findById(id);
   	 	Gym gym = gymDetails.orElseThrow(() -> new GymNotFoundException("Gym Not Found"));
   	 	
   	 	gymRepository.deleteById(id);
    }

    public void updateGym(GymDto gymDto, Long id) {
    	Gym updatedGym = gymRepository.findById(id).orElseThrow(()-> new GymNotFoundException("Gym not found"));
    	
    	updatedGym.setName(gymDto.getName());
    	updatedGym.setAddress(gymDto.getAddress());
    	updatedGym.setContactNo(gymDto.getContactNo());
    	updatedGym.setMembershipPlans(gymDto.getMembershipPlans());
    	updatedGym.setFacilities(gymDto.getFacilities());
    	
    	
    	gymRepository.save(updatedGym);
    }

    public void createGym(GymDto gymDto) {
    	Gym updatedGym = new Gym();
    	updatedGym.setName(gymDto.getName());
    	updatedGym.setAddress(gymDto.getAddress());
    	updatedGym.setContactNo(gymDto.getContactNo());
    	updatedGym.setMembershipPlans(gymDto.getMembershipPlans());
    	updatedGym.setFacilities(gymDto.getFacilities());
    	
    	
    	gymRepository.save(updatedGym);
    }

    public void addMember(Long userId, Long gymId) {
    	User user = userRepository.findById(userId).orElseThrow(() ->new UserNotFoundException("User Not found"));
    	Gym gymDetails = gymRepository.findById(gymId).orElseThrow(()-> new GymNotFoundException("Gym Not Found"));
    	user.setGym(gymDetails);
    	userRepository.save(user);
    	
    }

    public void deleteMember(Long userId, Long gymId) {
    	User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not found"));
 
    	Gym gymDetails = gymRepository.findById(gymId).orElseThrow(() -> new GymNotFoundException("Gym Not found"));
    	
    	if(user.getGym().getId() != gymId) {
    		throw new UserNotFoundException("User Not Found in the gym");
    	}
    	user.setGym(null);
    	
    	userRepository.save(user);
    }
}
