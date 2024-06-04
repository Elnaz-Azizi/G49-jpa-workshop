package se.lexicon.g49jpaworkshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g49jpaworkshop.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {

    List<BookLoan> findByBorrowerId(int borrowerId);

    List<BookLoan> findByBookId(int bookId);

    List<BookLoan> findByReturnedFalse();

    @Query("SELECT bl FROM BookLoan bl WHERE bl.dueDate < CURRENT_DATE AND bl.returned = false")
    List<BookLoan> findOverdueBookLoans();

    @Query("SELECT bl FROM BookLoan bl WHERE bl.loanDate BETWEEN :startDate AND :endDate")
    List<BookLoan> findBookLoansBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Transactional
    @Modifying
    @Query("UPDATE BookLoan bl SET bl.returned = true WHERE bl.id = :loanId")
    void markAsReturned(@Param("loanId") int loanId);
}
