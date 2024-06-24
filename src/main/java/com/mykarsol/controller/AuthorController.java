package com.mykarsol.controller;

import com.mykarsol.model.Author;
import com.mykarsol.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/list")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "listAuthors";
    }

    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable("id") Long id, Model model) {
        Optional<Author> author = authorService.findById(id);
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @PostMapping("/edit/{id}")
    public String updateAuthor(@PathVariable("id") Long id, @ModelAttribute Author author) {
        author.setId(id);
        //author.setName(author.getName());)
        authorService.save(author);
        return "redirect:/authors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteById(id);
        return "redirect:/authors/list";
    }
}
