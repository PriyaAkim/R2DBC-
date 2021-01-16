package com.ets.r2dbc.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.ets.r2dbc.model.Book;

import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveCrudRepository<Book, Long>{
 
	public Mono<Book> addBook(Book book);
}
