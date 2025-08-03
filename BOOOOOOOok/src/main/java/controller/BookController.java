package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import model.Book;

@Controller
public class BookController {

    @GetMapping("/BookStore/books")
    public String bookPage(Model model) {
        // 예시 데이터: 실제면 서비스/DAO에서 가져와야 함
        List<Book> books = Arrays.asList(
            new Book(1L, "자바 웹 개발 입문", "김철수",
                     "이 책은 자바 웹 개발의 기초부터 실전까지 다룬 입문서입니다.",
                     25000, 10, "/resources/images/java_web.jpg"),
            new Book(2L, "스프링 부트 실전", "이영희",
                     "스프링 부트를 활용한 실제 프로젝트 구성과 배포 전략을 설명합니다.",
                     32000, 5, "/resources/images/spring_boot.jpg"),
            new Book(3L, "데이터베이스 설계", "박민수",
                     "정규화, 인덱스, 성능 튜닝까지 포함한 데이터베이스 설계 완전 정복서.",
                     28000, 7, "/resources/images/db_design.jpg")
        );
        model.addAttribute("books", books);
        return "book"; // /WEB-INF/views/book.jsp
    }
}
