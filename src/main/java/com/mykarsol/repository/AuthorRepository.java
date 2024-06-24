package com.mykarsol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mykarsol.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
