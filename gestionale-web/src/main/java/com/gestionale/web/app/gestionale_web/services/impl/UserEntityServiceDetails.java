package com.gestionale.web.app.gestionale_web.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestionale.web.app.gestionale_web.models.Role;
import com.gestionale.web.app.gestionale_web.models.UserEntity;

@Service
public class UserEntityServiceDetails implements UserDetailsService{
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user1 = userServiceImpl.findByUserName(username);
        if(user1.isEmpty()){
            throw new UsernameNotFoundException("User doesnt exist in the system!");
        }
        UserEntity user2=user1.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user2.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        if (authorities.isEmpty()) {
            throw new UsernameNotFoundException("User: "+user2.getUsername() +" doesnt have any role asigned!");
        }
        User user3 = new User(user2.getUsername(), user2.getPasword(), user2.isEnable(), true, true, true, authorities);
        return user3;
    }
    
}
