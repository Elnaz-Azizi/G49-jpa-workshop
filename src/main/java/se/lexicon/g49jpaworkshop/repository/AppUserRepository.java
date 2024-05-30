package se.lexicon.g49jpaworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g49jpaworkshop.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

    List<AppUser> findAllByRegDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<AppUser> findByUserDetails_Id(int detailsId);

    Optional<AppUser> findByUserDetails_EmailIgnoreCase(String email);
}
