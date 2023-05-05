package com.kaianluciano.bookstore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tb_book")
@Getter @Setter
public class BookModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;

    @Column(nullable = false, unique = true, length = 50)
    private String titulo;

    @Column(nullable = false, unique = false, length = 50)
    private String autor;

    @Column(nullable = false, unique = false, length = 30)
    private String genero;

    @Column(nullable = false, unique = false, length = 6)
    private Double preco;

}
