package ru.Krasotkin.FavoriteBooks.Model;

import javax.persistence.*;

@Entity
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    private String annotation;

    @ManyToOne
    @JoinColumn(name = "userwhoadded_id")
    private UserModel userwhoadded;

    public UserModel getUserwhoadded() {
        return userwhoadded;
    }

    public void setUserwhoadded(UserModel userwhoadded) {
        this.userwhoadded = userwhoadded;
    }

    public BookModel() {

    }

    public BookModel(String name, String author, String annotation, UserModel userwhoadded) {
        this.name = name;
        this.author = author;
        this.annotation = annotation;
        this.userwhoadded = userwhoadded;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
