package se.lexicon.g49jpaworkshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import se.lexicon.g49jpaworkshop.entity.AppUser;
import se.lexicon.g49jpaworkshop.entity.Details;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
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
}
