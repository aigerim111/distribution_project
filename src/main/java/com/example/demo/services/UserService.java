package com.example.demo.services;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.EmailToken;
import com.example.demo.entities.Organization;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.EmailTokenRepo;
import com.example.demo.repositories.OrganizationRepo;
import com.example.demo.repositories.RoleRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final OrganizationRepo orgRepo;
    private final RoleRepo roleRepo;
    private final EmailTokenRepo tokenRepo;
    private final MailSenderService mailSender;

    public UserService(UserRepo userRepo, OrganizationRepo orgRepo, RoleRepo roleRepo, EmailTokenRepo tokenRepo, MailSenderService mailSender) {
        this.userRepo = userRepo;
        this.orgRepo = orgRepo;
        this.roleRepo = roleRepo;
        this.tokenRepo = tokenRepo;
        this.mailSender = mailSender;
    }

    public User registerUser(String email, String userRole, Long orgId) {
        User user = new User();
        Organization organization = orgRepo.findByOrgId(orgId).orElseThrow();
        Role role = roleRepo.findByName(userRole).orElseThrow();
        user.setOrg(organization);
        user.getRoles().add(role);

        EmailToken emailToken = new EmailToken(user);

        mailSender.sendMail(email, organization.getNameRu(), emailToken.getToken());
        tokenRepo.save(emailToken);
        return userRepo.save(user);
    }

    public void activateUser(String token) throws Exception {
        EmailToken emailToken = tokenRepo.findByToken(token).orElseThrow();
        User user = emailToken.getUser();
        if (!emailToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            user.setActive(true);
            userRepo.save(user);
            tokenRepo.delete(emailToken);
        } else {
            throw new Exception("Something went wrong");
        }
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }
}

    /*public User verifyUser(LoginDTO loginDTO, String email){
        User user = userRepo.findByEmail(email).orElseThrow();
        user.setUsername(loginDTO.getUsername());
        user.setEmail(loginDTO.getEmail());
        user.setPassword(encoder.encode(loginDTO.getPassword()));

        Role role = roleRepo.findByName("user").orElseThrow(); //demo
        user.setOrg(organization);
        user.getRoles().add(role);

        return userRepo.save(user);
    }

    public User addUserData(UserDTO userDTO, Long userId){
        User user = userRepo.findByUserId(userId).orElseThrow();
        user.setNameKz(userDTO.getNameKZ());
        user.setNameRu(userDTO.getNameRu());
        user.setPhone(userDTO.getPhone());

        return userRepo.save(user);
    }
}
