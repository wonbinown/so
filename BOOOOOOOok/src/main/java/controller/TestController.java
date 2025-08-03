package controller;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mapper.UserMapper;
import model.User;



@RestController
public class TestController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1) DB 연결 테스트
    @GetMapping("/test/db")
    public String testDb() {
        try (Connection conn = dataSource.getConnection()) {
            return "DB OK: " + conn.getMetaData().getURL();
        } catch (Exception e) {
            return "DB FAIL: " + e.getMessage();
        }
    }

    // 2) MyBatis 매퍼 테스트
    @GetMapping("/test/user")
    public Object testUser(@RequestParam String username) {
        User u = userMapper.findByUsername(username);
        return (u != null) ? u : "User not found";
    }

    // 3) PasswordEncoder 테스트
    @GetMapping("/test/encode")
    public String testEncode(@RequestParam String raw) {
        return passwordEncoder.encode(raw);
    }
}
