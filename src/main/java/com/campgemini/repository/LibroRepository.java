package com.campgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campgemini.model.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long>{

}
