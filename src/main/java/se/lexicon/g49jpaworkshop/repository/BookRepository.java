package se.lexicon.g49jpaworkshop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.g49jpaworkshop.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByIsbnIgnoreCase(String isbn);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);

    @Query("SELECT b FROM Book b JOIN b.bookLoans bl WHERE bl.returned = false")
    List<Book> findBooksOnLoan();

    @Query("SELECT b FROM Book b JOIN b.bookLoans bl WHERE bl.dueDate < CURRENT_DATE AND bl.returned = false")
    List<Book> findOverdueBooks();

    @Query("SELECT b FROM Book b JOIN b.bookLoans bl WHERE bl.loanDate BETWEEN :startDate AND :endDate")
    List<Book> findBooksLoanedBetweenDates(LocalDate startDate, LocalDate endDate);
}
