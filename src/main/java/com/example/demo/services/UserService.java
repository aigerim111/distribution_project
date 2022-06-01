package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Organization;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.OrganizationRepo;
import com.example.demo.repositories.RoleRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final OrganizationRepo orgRepo;
    private final RoleRepo roleRepo;

    private final MailSender mailSender;

    public UserService(UserRepo userRepo, OrganizationRepo orgRepo, RoleRepo roleRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.orgRepo = orgRepo;
        this.roleRepo = roleRepo;
        this.mailSender = mailSender;
    }

    //создание оболочки аккаунта работника со стороны админа
    public User createUser(String email, String roleName, Long orgId){
        User user = new User();
        user.setEmail(email);
        Role role = roleRepo.findByName(roleName).orElseThrow();
        user.getRoles().add(role);
        Organization organization = orgRepo.findByOrgId(orgId).orElseThrow();
        user.setOrg(organization);

        //activationCode
        user.setActivationCode(UUID.randomUUID().toString());

        mailSender.sendMail(email, organization.getNameRu(), user.getActivationCode());
        return userRepo.save(user);
    }

    /*public User verifyUser(LoginDTO loginDTO, String email){
        User user = userRepo.findByEmail(email).orElseThrow();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(encoder.encode(loginDTO.getPassword()));

        return userRepo.save(user);
    }*/

    public User addUserData(UserDTO userDTO, Long userId){
        User user = userRepo.findByUserId(userId).orElseThrow();
        user.setNameKz(userDTO.getNameKZ());
        user.setNameRu(userDTO.getNameRu());
        user.setPhone(userDTO.getPhone());

        return userRepo.save(user);
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code).orElseThrow();

        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);

        return true;
    }
}
