package com.aptech.project.project_sem4.service;

import com.aptech.project.project_sem4.model.User;
import com.aptech.project.project_sem4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    public User findUserbyId(String id)
    {
        return userRepository.findUserById(id);
    }


}
