package net.tplusable.hotpack.repository;

import net.tplusable.hotpack.entity.SiteUser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    void createUser(){
        SiteUser testUser = new SiteUser();
        testUser.setUsername("ellie");
        testUser.setPassword(passwordEncoder.encode("test"));
        testUser.setEmail("elliechoi@email.com");
        testUser.setName("ellie choi");
        userRepository.save(testUser);
        Assertions.assertEquals(2, userRepository.findAll().size());
    }
}
