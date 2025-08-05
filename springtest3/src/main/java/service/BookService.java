package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.BookMapper;
import model.Book;

@Service
public class BookService {

	@Autowired private BookMapper mapper;

	public List<Book> getAll() {
		return mapper.findAllBooks();
	}
	public Book getOne(Long id) {
		return mapper.findBookById(id);
	}
	// 관리자 기능 등…
}
