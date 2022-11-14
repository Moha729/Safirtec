package com.mo.safir.securityLevel.service;

import com.mo.safir.securityLevel.model.User;
import com.mo.safir.securityLevel.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    public final UserRepo repo;

    public List<User> fetchAll(){
        return repo.findAll();
    }

    public void addNew (User user){
        repo.save(user);
    }
}
