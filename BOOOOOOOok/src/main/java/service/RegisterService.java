package service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mapper.UserMapper;
import model.User;

@Service
public class RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean register(String name, String username, String rawPassword, String email) {
        // 아이디 중복 확인
        if (userMapper.findByUsername(username) != null) {
            return false;
        }

        // 비밀번호 형식 검사
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$");
        if (!pattern.matcher(rawPassword).matches()) {
            throw new IllegalArgumentException("비밀번호 형식이 맞지 않습니다.");
        }

        // 비밀번호 암호화
        String encoded = passwordEncoder.encode(rawPassword);

        // User 객체 생성
        User user = new User();
        user.setName(name);
        user.setLoginId(username);     // ← setUsername() ❌, 필드명에 맞춰야 해
        user.setPassword(encoded);
        user.setEmail(email);
        user.setRole("CUSTOMER");

        // DB에 저장
        userMapper.insertUser(user);
        return true;
    }
}
