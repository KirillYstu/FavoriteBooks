package ru.Krasotkin.FavoriteBooks.Controller;

import org.springframework.data.domain.Sort;
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
import ru.Krasotkin.FavoriteBooks.Repository.BookRepo;
import ru.Krasotkin.FavoriteBooks.Repository.BookmarkerRepo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private BookRepo bookRepo;
    private BookmarkerRepo bookmarkerRepo;

    public BookController(BookRepo bookRepo, BookmarkerRepo bookmarkerRepo) {
        this.bookRepo = bookRepo;
        this.bookmarkerRepo = bookmarkerRepo;
    }

    @GetMapping("/book")
    public String Book(Model model) {
        List<BookModel> resultList = new ArrayList<>();
        bookRepo.findAll(Sort.by(List.of(Sort.Order.desc("id")))).forEach(resultList::add);
        model.addAttribute("book", resultList);
        return "book";
    }

    @GetMapping("/book/add")
    public String BookAdd(Model model) {
        return "book-add";
    }

    @PostMapping("/book/add")
    public String BookAddPost(Model model, Authentication auth, @RequestParam String name, @RequestParam String author, @RequestParam String annotation) {
        BookModel book = new BookModel(name, author, annotation, (UserModel)auth.getPrincipal());
        bookRepo.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}/delete")
    public String BookDelete(Model model, Authentication auth, @PathVariable(value = "id") Long id) {
        if (!bookRepo.existsById(id)) {}
        else {
            BookModel bookModel = bookRepo.findById(id).orElseThrow();
            UserModel authuser =(UserModel) auth.getPrincipal();
            if(bookModel.getUserwhoadded().getId() == authuser.getId() || authuser.getId() == 1L) {
                bookRepo.deleteById(id);
            }
            else {}
        }
        return "redirect:/book";
    }

    @GetMapping("/book/{id}/edite")
    public String BookEdite(Model model, Authentication auth, @PathVariable(value = "id") Long id) {
        if (!bookRepo.existsById(id)) {return "redirect:/book";}
        else {
            BookModel bookModel = bookRepo.findById(id).orElseThrow();
            UserModel authuser =(UserModel) auth.getPrincipal();
            if(bookModel.getUserwhoadded().getId() == authuser.getId() || authuser.getId() == 1L) {
                model.addAttribute("book", bookModel);
                return "book-edite";
            }
            else {return "redirect:/book";}
        }
    }

    @PostMapping("/book/{id}/edite")
    public String BookEditePost(Model model, Authentication auth, @RequestParam String name, @RequestParam String author, @RequestParam String annotation, @PathVariable(value = "id") Long id) {
        BookModel bookModel = bookRepo.findById(id).orElseThrow();
        bookModel.setName(name);
        bookModel.setAuthor(author);
        bookModel.setAnnotation(annotation);
        bookRepo.save(bookModel);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}/addmark")
    public String BookAddMark(Model model, Authentication auth, @PathVariable(value = "id") Long id) {
        BookModel bookModel = bookRepo.findById(id).orElseThrow();
        UserModel userModel = (UserModel) auth.getPrincipal();
        BookmarkModel bookmarkModel = new BookmarkModel(userModel, bookModel);
        bookmarkerRepo.save(bookmarkModel);
        return "redirect:/book";
    }

}
