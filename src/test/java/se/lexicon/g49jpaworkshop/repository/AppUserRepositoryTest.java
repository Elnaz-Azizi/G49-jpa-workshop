package se.lexicon.g49jpaworkshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import se.lexicon.g49jpaworkshop.entity.AppUser;
import se.lexicon.g49jpaworkshop.entity.Details;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppUserRepositoryTest {


    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    public void testFindByUsername() {
        Details detailsObj = new Details("Elnaz", "azizi.elnaz66@gmail.com", LocalDate.parse("1987-09-20"));
        Details createdDetails = detailsRepository.save(detailsObj);
        assertNotNull(createdDetails);

        AppUser appUserObj = new AppUser("NickNick", "123456");
        appUserObj.setUserDetails(createdDetails);
        AppUser createdAppUser = appUserRepository.save(appUserObj);
        assertNotNull(createdAppUser);

        Optional<AppUser> found = appUserRepository.findByUsername("NickNick");
        assertTrue(found.isPresent());
    }

    @Test
    public void testFindAllByRegDateBetween() {
        Details detailsObj1 = new Details("Nickan@yahoo.com", "Nickan", LocalDate.parse("2022-02-25"));
        detailsRepository.save(detailsObj1);

        AppUser appUserObj1 = new AppUser("Nicky", "567890");
        appUserObj1.setRegDate(LocalDate.parse("2023-05-10"));
        appUserObj1.setUserDetails(detailsObj1);
        appUserRepository.save(appUserObj1);

        Details detailsObj2 = new Details("Marry@gmail.com", "Marryam", LocalDate.parse("1989-12-25"));
        detailsRepository.save(detailsObj2);

        AppUser appUserObj2 = new AppUser("MaryMary", "786543");
        appUserObj2.setRegDate(LocalDate.parse("2023-05-20"));
        appUserObj2.setUserDetails(detailsObj2);
        appUserRepository.save(appUserObj2);

        LocalDate startDate = LocalDate.parse("2023-05-01");
        LocalDate endDate = LocalDate.parse("2023-05-15");
        List<AppUser> users = appUserRepository.findAllByRegDateBetween(startDate, endDate);
        assertThat(users).hasSize(1).extracting(AppUser::getUsername).contains("Nicky");
    }
}
