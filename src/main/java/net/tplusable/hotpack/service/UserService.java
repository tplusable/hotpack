package net.tplusable.hotpack.service;

import lombok.RequiredArgsConstructor;
import net.tplusable.hotpack.exception.DataNotFoundException;
import net.tplusable.hotpack.entity.SiteUser;
import net.tplusable.hotpack.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// 생성자를 통한 bean 주입
@RequiredArgsConstructor
// 비즈니스로직을 담당하는 Service계층이라고 Spring에게 인식시킴
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // UserRepository를 이용하여 회원 데이터를 생성하는 메서드
    public SiteUser create(String username, String email, String password, String name) {
        // Siteuser 객체에 전달받은 username, email, password를 저장
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        // Siteuser 객체를 jpa를 이용해 db에 insert
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
