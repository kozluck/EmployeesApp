package com.kozluck.EmployeesApp.domain.services.user;

import com.kozluck.EmployeesApp.domain.models.user.User;
import com.kozluck.EmployeesApp.domain.repository.UserRepository;
import com.kozluck.EmployeesApp.domain.utils.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserService {


    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveUser(User user) throws UserAlreadyExistException {
        if (emailExist(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByUsernameIs(String username) {
        return userRepository.findByUsernameIs(username);
    }

    public User findOneById(Integer Id){
        return userRepository.findOneById(Id);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
