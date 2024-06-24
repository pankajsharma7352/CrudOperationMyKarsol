package com.mykarsol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mykarsol.model.Author;
import com.mykarsol.repository.AuthorRepository;

@Service
public class AuthorService {
	 @Autowired
	    private AuthorRepository authorRepository;

	    public List<Author> getAllAuthors()  {
	        return authorRepository.findAll();
	    }

	    public Author save(Author author) {
	        return authorRepository.save(author);
	    }

		public Optional<Author> findById(Long id) {
			// TODO Auto-generated method stub
			return authorRepository.findById(id);
		}

		public void deleteById(Long id) {
			authorRepository.deleteById(id);
		}

		

		

}
