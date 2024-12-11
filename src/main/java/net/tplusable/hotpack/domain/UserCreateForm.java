package net.tplusable.hotpack.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    // 문자열의 길이가 최소, 최대 길이에 해당하는지 검증
    @Size(min=3, max=25)
    @NotEmpty(message="사용자ID는 필수항목입니다.")
    private String username;

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;

    @NotEmpty(message="비밀번호는 필수항목입니다.")
    private String password1;

    // 비밀번호 확인하는 속성
    @NotEmpty(message="비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message="이메일은 필수항목입니다.")
    // 해당 속성의 값이 이메일 형식과 일치하는지 검증
    @Email
    private String email;
}
