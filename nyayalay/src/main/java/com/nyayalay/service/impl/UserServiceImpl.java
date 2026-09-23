package com.nyayalay.service.impl;

import com.nyayalay.entity.User;
import com.nyayalay.exception.ResourceNotFoundException;
import com.nyayalay.repository.UserRepository;
import com.nyayalay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: "+id
                        )
                );
    }

    @Override
    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "User not found with id: "+id
                        )
                );
        userRepository.delete(user);
    }
}
