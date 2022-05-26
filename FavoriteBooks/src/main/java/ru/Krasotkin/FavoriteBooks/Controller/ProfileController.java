package ru.Krasotkin.FavoriteBooks.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Krasotkin.FavoriteBooks.Model.BookModel;
import ru.Krasotkin.FavoriteBooks.Model.BookmarkModel;
import ru.Krasotkin.FavoriteBooks.Model.UserModel;
import ru.Krasotkin.FavoriteBooks.Repository.BookmarkerRepo;
import ru.Krasotkin.FavoriteBooks.Repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    private UserRepo userRepo;
    private BookmarkerRepo bookmarkerRepo;

    public ProfileController(UserRepo userRepo, BookmarkerRepo bookmarkerRepo) {
        this.userRepo = userRepo;
        this.bookmarkerRepo = bookmarkerRepo;
    }

    @GetMapping("/profile")
    public String Profile(Model model, Authentication auth) {
        if (auth == null) {
            return "redirect:/registration";
        }
        else {
            Optional<UserModel> us= userRepo.getByUsername(auth.getName());
            List<UserModel> user = new ArrayList<>();
            us.ifPresent(user::add);
            model.addAttribute("user", user);
            return "profile";
        }
    }

    @GetMapping("/profile/{id}/edit")
    public String profileEdit (@PathVariable(value = "id") long id, Model model, Authentication auth) {
        Optional<UserModel> us= userRepo.getByUsername(auth.getName());
        List<UserModel> user = new ArrayList<>();
        us.ifPresent(user::add);
        if (user.get(0).getId() != id) {
            return "redirect:/profile";
        } else {
            model.addAttribute("user", user);
            return "profile-edit";
        }
    }

    @PostMapping("/profile/{id}/edit")
    public String profilePostEdit(@PathVariable(value = "id") long id, @RequestParam String name , @RequestParam String surname, @RequestParam String patronymic, @RequestParam String password, Model model) {
        UserModel user = userRepo.findById(id).orElseThrow();
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPassword(password);
        userRepo.save(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/{id}/bookstoread")
    public String bookToRead(Model model, Authentication auth, @PathVariable(value = "id") long id) {
        List<BookmarkModel> book = bookmarkerRepo.getAllByUser((UserModel)auth.getPrincipal());
        model.addAttribute("book", book);
        return "profile-book";
    }

    @GetMapping("/profile/bookstoread/{id}/delete")
    public String bookToReadDelete(Model model, Authentication auth, @PathVariable(value = "id") long id) {
        bookmarkerRepo.deleteById(id);
        return "redirect://profile/{id}/bookstoread";
    }

}
