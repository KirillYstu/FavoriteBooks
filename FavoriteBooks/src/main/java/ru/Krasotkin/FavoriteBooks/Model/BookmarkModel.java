package ru.Krasotkin.FavoriteBooks.Model;

import javax.persistence.*;

@Entity
public class BookmarkModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public UserModel getUser() {
        return user;
    }


    public void setUser(UserModel user) {
        this.user = user;
    }

    public BookmarkModel() {}

    public BookmarkModel(UserModel user, BookModel book) {
        this.user = user;
        this.book = book;
    }
}
