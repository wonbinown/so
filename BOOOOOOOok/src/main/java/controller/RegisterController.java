package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.RegisterService;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    // ① GET /register: 폼 표시
    @GetMapping("/register")
    public String showForm() {
        return "login/registerForm"; // 회원가입 폼 JSP
    }

    // ② POST /register: 데이터 처리
    @PostMapping("/register")
    public String doRegister(@RequestParam String name,
                             @RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String confirmPassword,
                             @RequestParam String email,
                             RedirectAttributes ra) {
        // 비밀번호 일치 확인
        if (!password.equals(confirmPassword)) {
            ra.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/register";
        }

        // 비밀번호 형식 검사
        String pwRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$";
        if (!password.matches(pwRegex)) {
            ra.addFlashAttribute("error", "비밀번호는 8자 이상이며, 대문자+소문자+숫자+특수문자를 포함해야 합니다.");
            return "redirect:/register";
        }

        // 회원가입 시도
        boolean ok = registerService.register(name, username, password, email);
        if (!ok) {
            ra.addFlashAttribute("error", "이미 존재하는 아이디입니다.");
            return "redirect:/register";
        }

        // 성공 시
        System.out.println("입력된 비밀번호: [" + password + "]");
        return "redirect:/register/success";
    }

    // ③ GET /register/success: 성공 페이지
    @GetMapping("/register/success")
    public String showSuccess() {
        return "login/registerSuccess"; // 회원가입 완료 페이지 JSP
    }
}
