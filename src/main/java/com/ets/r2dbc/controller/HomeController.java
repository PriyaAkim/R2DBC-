package com.ets.r2dbc.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ets.r2dbc.model.Book;
import com.ets.r2dbc.repository.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/books")
public class HomeController {

	@Autowired BookRepository bookRepository;

    @GetMapping("")
    public Flux<Book> getHome() {

        return bookRepository.findAll();

    }

    @PostMapping("/addbook")
    public Mono<Book> postBook(@RequestBody Book book) {
    
    	Mono<Book> a = null;
		try {
//				if(book.getId() == null) {
//				throw new IllegalAccessException("Id is Required");
//				}
				a = bookRepository.addBook(book);
		
		}catch (NoSuchElementException nsex) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
					"<GAPO0401> : unable to generate unique Id");
		}catch (DataIntegrityViolationException divex) {
			divex.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"<GAPO0402> : looks like something wrong with your data, database insert error occured, constraints check failed");
		}catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"<GAPO0405> : internal server error, Please contact admin");
		}
		return a;
		
    }

    @PutMapping("")
    public Mono<Book> updateBook(@RequestBody Book book) {

        return bookRepository.save(book);

    }

    @DeleteMapping("")
    public boolean deleteBook(@RequestBody Book book) {

        try {
            bookRepository.deleteById(book.getId()).block(); // Note this!
            return true;

        } catch (Exception e) {

            return false;
        }
    }
}
