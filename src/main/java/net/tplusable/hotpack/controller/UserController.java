package net.tplusable.hotpack.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.tplusable.hotpack.dto.UserCreateForm;
import net.tplusable.hotpack.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // /user/signup URL이 GET으로 요청되면 회원가입을 위한 템플릿을 렌더링
    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        // signup_form으로 화면 렌더링
        return "signup_form";
    }

    // /user/signup URL이 POST로 요청되면 회원가입을 진행
    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        // 회원가입시 password1과 password2가 동일한지 검증하는 조건문
        // bindingResult.rejectValue를 사용하여 입력받은 2개의 비밀번호가 일치하지 않으면 오류 발생
        // bindingResult.rejectValue(필드명, 오류코드, 오류메세지)
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        // 사용자 ID 또는 이메일 주소가 이미 존재할 경우 "이미 등록된 사용자입니다"라는 오류메세지 화면에 표시
        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(), userCreateForm.getPassword1(), userCreateForm.getName());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        // /로 화면 리렌더링
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

//    @GetMapping("/userinfo")
//    public String userinfo() {
//        userService.getUser()
//        return "login_userinfo";
//    }
}
