package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void addUser(User user) {
        userRepository.save(user);
    }
    public boolean updateUser(Integer id,User user) {
        User user1 = userRepository.getReferenceById(id);
        if(user1 != null) {
            user1.setName(user.getName());
            user1.setAge(user.getAge());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setRole(user.getRole());
            userRepository.save(user1);
            return true;
        }
        return false;

    }
    public boolean deleteUser(Integer id) {
        User u=userRepository.getReferenceById(id);
        if(u==null) {
            return false;
        }
        userRepository.delete(u);
        return true;
    }
}
