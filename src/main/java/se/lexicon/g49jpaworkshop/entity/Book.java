package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data

@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String title;
    private int maxLoanDays;

    @Column(nullable = false)
    private boolean available = true;

    @OneToMany(mappedBy = "book")
    private Set<BookLoan> bookLoans= new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }
    public void addBookLoan(BookLoan bookLoan) {
        if (this.available) {
            this.bookLoans.add(bookLoan);
            bookLoan.setBook(this);
            this.available = false;
        } else {
            throw new IllegalStateException("Book is not available for loan.");
        }
    }
}