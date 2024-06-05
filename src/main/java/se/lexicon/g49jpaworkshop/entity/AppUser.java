package se.lexicon.g49jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @OneToOne
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private Details userDetails;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookLoan> bookLoans = new HashSet<>();


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.regDate = LocalDate.now();
    }

    public void addBookLoan(BookLoan bookLoan) {
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
    }

    public void removeBookLoan(BookLoan bookLoan) {
        bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);
    }
}

