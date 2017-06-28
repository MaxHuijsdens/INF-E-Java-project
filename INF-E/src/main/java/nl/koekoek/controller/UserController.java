package nl.koekoek.controller;

import java.util.ArrayList;
import java.util.List;

import nl.koekoek.model.Server;
import nl.koekoek.model.ServerUser;
import nl.koekoek.model.ServerUserChange;
import nl.koekoek.model.User;
import nl.koekoek.service.UserService;
import nl.koekoek.support.RestResult;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for class user.
 * @author bastiaan
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    
    /**
     * Constructor with the userService, autowired.
     * @param userService the userService
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    };

    /**
     * Returns all users.
     * @return List with all users
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUserList() {
        return userService.getAllUsers();
    }

    /**
     * Updates a list of users.
     * @param userList list of users
     * @return Restresult for a user
     */
    @RequestMapping(value = "/saveUserList", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<List<User>> saveUserList(@RequestBody List<User> userList) {
        return RestResult.success(this.userService.saveUserList(userList));
    }

    /**
     * Checks if an user is in a server, when he is not adds him to the server. also adds a record to the user change table. 
     * This so you know when a user has left or joined a server.
     * @param userData all users in a server
     * @param sudoUserData all sudo users in a server
     * @param server the server
     * @param timeOfMeasurement what time this data is measured
     */
    public void addUsers(List<String> userData, List<String> sudoUserData, Server server, LocalDateTime timeOfMeasurement) {
        List<Long> addedUsers = new ArrayList<Long>();
        for (String u : userData) {
            if (userService.getUserByName(u) == null) {
                User user = new User(u);
                userService.addUser(user);
            }
            User user = userService.getUserByName(u);
            ServerUser serverUser = null;
            if (userService.getServerUser(server.getId(), user.getId()) == null) {
                serverUser = new ServerUser(server, user);
                userService.addServerUser(serverUser);

                ServerUserChange serverUserChange = new ServerUserChange(server, user, timeOfMeasurement, true);
                userService.addServerUserChange(serverUserChange);
            } else {
                serverUser = userService.getServerUser(server.getId(), user.getId());
            }
            if (serverUser != null) {
                for (String sudoUser : sudoUserData) {
                    if (user.getName().equals(sudoUser)) {
                        serverUser.setSudo(true);
                        userService.addServerUser(serverUser);
                    }
                }
            }
            addedUsers.add(user.getId());
        }
        List<ServerUser> deletedUsers = userService.getUserNotInServer(addedUsers, server.getId());
        for (ServerUser su : deletedUsers) {
            userService.deleteServerUser(su);
            ServerUserChange serverUserChange = new ServerUserChange(server, su.getUser(), timeOfMeasurement, false);
            userService.addServerUserChange(serverUserChange);
        }
    };
}
