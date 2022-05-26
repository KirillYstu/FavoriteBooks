package ru.Krasotkin.FavoriteBooks.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Krasotkin.FavoriteBooks.Model.RoleModel;
import ru.Krasotkin.FavoriteBooks.Model.UserModel;
import ru.Krasotkin.FavoriteBooks.Repository.RoleRepo;
import ru.Krasotkin.FavoriteBooks.Repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RegistrationController {
    private UserRepo userRepo;
    private RoleRepo roleRepo;

    public RegistrationController(UserRepo user, RoleRepo role) {
        this.userRepo = user;
        this.roleRepo = role;
    }

    @GetMapping("/registration")
    public String Registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String RegistrationPost(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String surname, @RequestParam String name, @RequestParam String patronymic) {
        if(userRepo.existsByUsername(username)) {
           return "redirect:/registration";
        }
        else {
            RoleModel role = new RoleModel(2L, "REGISTEREDUSER");
            UserModel user = new UserModel(username, password, surname, name, patronymic, role);
            userRepo.save(user);
            return "redirect:/login";
        }
    }
}
