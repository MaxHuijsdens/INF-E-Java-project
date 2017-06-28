package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.ServerUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerUserRepository extends JpaRepository<ServerUser, Long> {

    /**
     * Gets a serveruser object by serverid and userid.
     * @param serverId the server id
     * @param userId the user id
     * @return ServerUser object
     */
    ServerUser findByServerIdAndUserId(Long serverId, Long userId);

    /**
     * Gets a list off all serverUSers by a server ID.
     * @param serverId server id
     * @return list of serverUsers
     */
    List<ServerUser> findByServerId(Long serverId);

    /**
     * Gets all users that are not in a server anymore.
     * @param userId all usersids that are currently in a server
     * @param serverId server id
     * @return list with server users
     */
    List<ServerUser> findByUserIdNotInAndServerId(List<Long> userId, long serverId);
}
