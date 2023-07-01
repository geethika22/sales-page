package com.SpringApp.SpringApp.controller;

import com.SpringApp.SpringApp.exception.UserNotFoundException;
import com.SpringApp.SpringApp.model.User;
import com.SpringApp.SpringApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();

    }
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id)
                .map(user ->{
                    user.setName(newUser.getName());
                    user.setCustomer(newUser.getCustomer());
                    user.setOpportunity(newUser.getOpportunity());
                    user.setAmount(newUser.getAmount());
                    user.setDate(newUser.getDate());
                    user.setNotes(newUser.getNotes());
                    return userRepository.save(user);
                        }).orElseThrow(()->new UserNotFoundException(id));


    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "user with id "+id+ "has been deleted success.";
    }

}
