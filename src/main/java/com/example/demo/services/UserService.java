package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.EmailToken;
import com.example.demo.entities.Organization;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.EmailTokenRepo;
import com.example.demo.repositories.OrganizationRepo;
import com.example.demo.repositories.RoleRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final OrganizationRepo orgRepo;
    private final RoleRepo roleRepo;
    private final EmailTokenRepo tokenRepo;
    private final MailSender mailSender;

    public UserService(UserRepo userRepo, OrganizationRepo orgRepo, RoleRepo roleRepo, EmailTokenRepo tokenRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.orgRepo = orgRepo;
        this.roleRepo = roleRepo;
        this.tokenRepo = tokenRepo;
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
//        (UUID.randomUUID().toString());

        EmailToken emailToken = new EmailToken(user);

        mailSender.sendMail(email, /*organization.getNameRu(),*/ emailToken.getToken());
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

    //Создать свою ошибку
    public void activateUser(String token) throws Exception {
        EmailToken emailToken = tokenRepo.findByToken(token).orElseThrow();
        User user = emailToken.getUser();
        if (emailToken.getExpiryDate().isBefore(LocalDateTime.now())){
            user.setActive(true);
            userRepo.save(user);
            tokenRepo.delete(emailToken);
        }
        else{
            throw new Exception("Something went wrong");
        }

    }
}
