package com.CN.Gym.service;

import com.CN.Gym.dto.UserRequest;
import com.CN.Gym.dto.WorkoutDto;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Role;
import com.CN.Gym.model.User;
import com.CN.Gym.model.Workout;
import com.CN.Gym.repository.UserRepository;
import com.CN.Gym.repository.WorkoutRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    WorkoutRepository workoutRepository;

    public List<User> getAllUsers() {
    	return userRepository.findAll();
    }

    public void createUser(UserRequest userRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userRequest.getPassword());
        User user = User.builder().email(userRequest.getEmail()).age(userRequest.getAge())
                .gender(userRequest.getGender()).password(encodedPassword)
                .build();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        if(userRequest.getUserType() != null) {
            if (userRequest.getUserType().equalsIgnoreCase("TRAINER")) {
                role.setRoleName("ROLE_TRAINER");
                roles.add(role);
                user.setRoles(roles);
            } else if (userRequest.getUserType().equalsIgnoreCase("ADMIN")) {
                role.setRoleName("ROLE_ADMIN");
                roles.add(role);
                user.setRoles(roles);
            } else {
                role.setRoleName("ROLE_CUSTOMER");
                roles.add(role);
                user.setRoles(roles);
            }
        }
        else {
            role.setRoleName("ROLE_CUSTOMER");
            roles.add(role);
            user.setRoles(roles);
        }
        userRepository.save(user);
    }

    public User getUserById(Long id) {
    	User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));

    	return user;
    }

    public void updateUser(UserRequest userRequest, Long id) {
    	User user = getUserById(id);
    	
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String encodedPassword = encoder.encode(userRequest.getPassword());
    	user.setAge(userRequest.getAge());
    	user.setEmail(userRequest.getEmail());
    	user.setGender(userRequest.getGender());
    	user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    public void deleteUser(Long id){
    	User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    	userRepository.deleteById(id);
    }

    public void addWorkout(WorkoutDto workoutDto, Long userId) {
    	User user = getUserById(userId);
    	Workout workout = Workout.builder().workoutName(workoutDto.getWorkoutName()).description(workoutDto.getDescription())
    			.difficultyLevel(workoutDto.getDifficultyLevel()).duration(workoutDto.getDuration()).build();
    	workout.setUser(user);
    	workoutRepository.save(workout);
   
    	
    }
}
