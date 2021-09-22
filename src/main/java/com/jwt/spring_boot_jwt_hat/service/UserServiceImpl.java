package com.jwt.spring_boot_jwt_hat.service;

import com.jwt.spring_boot_jwt_hat.authen.UserPrincipal;
import com.jwt.spring_boot_jwt_hat.entity.UserEntity;
import com.jwt.spring_boot_jwt_hat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserPrincipal findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();

        if (user != null){
            Set<String> authorities = new HashSet<>();
            if (user.getRoles() != null)
                user.getRoles().forEach(r -> {
                    authorities.add(r.getRoleKey());
                    r.getPermissions().forEach(p->authorities.add(p.getPermissionKey()));
                });
            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }
}
