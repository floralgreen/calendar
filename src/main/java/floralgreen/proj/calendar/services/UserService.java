package floralgreen.proj.calendar.services;

import floralgreen.proj.calendar.entities.User;
import floralgreen.proj.calendar.entities.enums.RecordStatusEnum;
import floralgreen.proj.calendar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a new User object on the DB
     * @param user
     * @return the Object just created
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Given a userId
     * @param userId
     * @return Optional of User with the objectFound
     *  or Optional.empty if the Id doesn't exists or the User is has status "D"
     */
    public Optional<User> findActiveUserById(Long userId){
        return userRepository.findActiveUserById(userId);
    }

    /**
     *
     * @return a list with all the users with record status "A"
     */
    public List<User> findAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }

    /**
     * Given a userId and an updated user Object
     * @param userId
     * @param userUpdate
     * @return the updated User saved on the DB
     * empty optional if the userId is not existing or the user has status "D"
     */
    public Optional<User> updateUserById(Long userId, User userUpdate) {
        Optional<User> userFound = findActiveUserById(userId);
        if (userFound.isPresent()) {
            userFound.get().setUsername(userUpdate.getUsername());
            userFound.get().setPassword(userUpdate.getPassword());
            userFound.get().setEmail(userUpdate.getEmail());
            userFound.get().setFirstName(userUpdate.getFirstName());
            userFound.get().setLastName(userUpdate.getLastName());
            userRepository.save(userFound.get());
        }
        return userFound;
    }


    /**
     *
     * @param userId given a user Id
     * @return the deleted user
     * or an empty optional if the user doesn't exists or the user is already deleted
     */
    public Optional<User> deleteUserById(Long userId) {
        Optional<User> userFound = findActiveUserById(userId);
        if(userFound.isPresent()) {
            userFound.get().setRecordStatus(RecordStatusEnum.D);
            userRepository.save(userFound.get());
        }
        return userFound;
    }

}
