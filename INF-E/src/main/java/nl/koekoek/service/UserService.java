package nl.koekoek.service;

import java.util.List;

import nl.koekoek.model.ServerUser;
import nl.koekoek.model.ServerUserChange;
import nl.koekoek.model.User;
import nl.koekoek.repository.ServerUserChangeRepository;
import nl.koekoek.repository.ServerUserRepository;
import nl.koekoek.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Userservice, talks to the repository.
 * @author bastiaan
 *
 */
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private ServerUserRepository serverUserRepository;
    private ServerUserChangeRepository serverUserChangeRepository;

    /**
     * Default contructor.
     */
    public UserService() {

    };

    /**
     * Contructor with all services used.
     * @param userRepository the user repository
     * @param serverUserRepository the serveruser repository
     * @param serverUserChangeRepository the server user change repository
     */
    @Autowired
    public UserService(UserRepository userRepository, ServerUserRepository serverUserRepository, ServerUserChangeRepository serverUserChangeRepository) {
        this.userRepository = userRepository;
        this.serverUserRepository = serverUserRepository;
        this.serverUserChangeRepository = serverUserChangeRepository;

    };

    /**
     * Adds a user.
     * @param user the user to add
     * @return user that is added
     */
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Adds serverUser.
     * @param serverUser the server user to add
     * @return serverUser that is added
     */
    public ServerUser addServerUser(ServerUser serverUser) {
        return this.serverUserRepository.save(serverUser);
    }

    /**
     * Adds ServerUserChange. 
     * @param serverUserChange the ServerUserChange to add
     * @return ServerUserChange that is added
     */
    public ServerUserChange addServerUserChange(ServerUserChange serverUserChange) {
        return this.serverUserChangeRepository.save(serverUserChange);
    }

    /**
     * Gets a user by its name.
     * @param name the name of the user
     * @return User object
     */
    public User getUserByName(String name) {
        return this.userRepository.findByName(name);
    }

    /**
     * Gets a server user Obejct by server en user.
     * @param serverId the sever id
     * @param userId the user id
     * @return ServerUser Object 
     */
    public ServerUser getServerUser(Long serverId, Long userId) {
        return this.serverUserRepository.findByServerIdAndUserId(serverId, userId);
    }

    /**
     * Gets all users not in a server anymore.
     * @param userIds all user ids
     * @param serverId the server id
     * @return list with server users
     */
    public List<ServerUser> getUserNotInServer(List<Long> userIds, long serverId) {
        return this.serverUserRepository.findByUserIdNotInAndServerId(userIds, serverId);
    }

    /**
     * Deletes a server User.
     * @param serverUser the server user to delete
     */
    public void deleteServerUser(ServerUser serverUser) {
        this.serverUserRepository.delete(serverUser);
    }

    /**
     * Gets all users.
     * @return list of users
     */
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    };

    /**
     * saves a list of users.
     * @param userList to be saves
     * @return saved user list
     */
    public List<User> saveUserList(List<User> userList) {
        return this.userRepository.save(userList);
    };
}
