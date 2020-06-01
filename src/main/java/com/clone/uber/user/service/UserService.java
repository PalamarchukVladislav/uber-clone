package com.clone.uber.user.service;

import com.clone.uber.taxi.entity.Taxi;
import com.clone.uber.taxi.service.TaxiService;
import com.clone.uber.user.entity.User;
import com.clone.uber.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).get(); // TODO fix it
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(User changedUser) {
        User userToUpdate = userRepository.getOne(changedUser.getId());

        userToUpdate.setId(changedUser.getId());
        userToUpdate.setName(changedUser.getName());
        userToUpdate.setUserMoney(changedUser.getUserMoney());
        userToUpdate.setTaxi(changedUser.getTaxi());

        userRepository.save(userToUpdate);
    }

}
