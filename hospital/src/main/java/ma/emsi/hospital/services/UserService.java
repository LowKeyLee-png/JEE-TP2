package ma.emsi.hospital.services;


import ma.emsi.hospital.entities.Role;
import ma.emsi.hospital.entities.User;
public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName,String roleName);
    User authentificate(String userName,String password);
}