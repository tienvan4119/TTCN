package com.aptech.project.project_sem4.service;

import com.aptech.project.project_sem4.model.Role;
import com.aptech.project.project_sem4.model.Section;
import com.aptech.project.project_sem4.model.Topic;
import com.aptech.project.project_sem4.model.User;
import com.aptech.project.project_sem4.repository.RoleRepository;
import com.aptech.project.project_sem4.repository.SectionRepository;
import com.aptech.project.project_sem4.repository.TopicRepository;
import com.aptech.project.project_sem4.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SectionRepository sectionRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void saveUser(User user) {
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
    public List<User> listAll()
    {
        return userRepository.findAll();
    }
    public List<Role> listAllRole()
    {
        return roleRepository.findAll();
    }
    public List<Section> listAllSection()
    {
        return sectionRepository.findAll();
    }
    public List<Section> getListSectionUnDone()
    {
        return sectionRepository.getAllSectionUnDone();
    }
    public User saveUserProfile(User user)
    {
        return userRepository.save(user);
    }

}
