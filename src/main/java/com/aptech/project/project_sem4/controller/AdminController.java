package com.aptech.project.project_sem4.controller;

import com.aptech.project.project_sem4.model.User;
import com.aptech.project.project_sem4.service.AdminService;
import com.aptech.project.project_sem4.service.QuizService;
import com.aptech.project.project_sem4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public User getUser(HttpServletRequest request)
    {
        String user_id = request.getParameter("user_id");
        User user = adminService.findUserbyId(user_id);
        return user;
    }
    @RequestMapping(value = "/changeProfile",method = RequestMethod.POST)
    public User updateUser(HttpServletRequest request)
    {
        String userEmail = request.getParameter("userEmail");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        User user = userService.findByEmail(userEmail);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        userService.saveUserProfile(user);
        return user;
    }
    @RequestMapping(value = "/DeleteUser", method = RequestMethod.POST)
    public String DeleteUser(HttpServletRequest request)
    {
        String user_id = request.getParameter("user_id");
        return "abc";
    }
}
