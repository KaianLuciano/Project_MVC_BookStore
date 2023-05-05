package com.kaianluciano.bookstore.repository;

import com.kaianluciano.bookstore.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    boolean existsByTitulo(String titulo);
}
