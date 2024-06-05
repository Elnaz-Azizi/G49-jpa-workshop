package se.lexicon.g49jpaworkshop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g49jpaworkshop.entity.Book;
import se.lexicon.g49jpaworkshop.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book bookNumber1;
    private Book bookNumber2;

    @BeforeEach
    public void setup() {
        bookNumber1 = new Book("0001","test book",14);
        bookNumber2 = new Book("0002","test Title 2", 21);


        bookRepository.save(bookNumber1);
        bookRepository.save(bookNumber2);
    }


}
