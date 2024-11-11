package com.example.tugas_firebase.model;

public class Book {

    public String title;
    public String author;
    public String description;
    public String cover;
    public String image;

    public Book() {
    }

    public Book(String title, String author, String description, String cover, String image) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.cover = cover;
    }
}
