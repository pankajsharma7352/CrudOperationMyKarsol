package com.mykarsol.controller;

import com.mykarsol.model.Record;
import com.mykarsol.service.RecordService;
import com.mykarsol.service.AuthorService;
import com.mykarsol.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private EmailService emailService;

    private static String UPLOAD_DIR = "src/main/resources/static/banners/";

    @GetMapping("/list")
    public String listRecords(Model model) {
        List<Record> records = recordService.getAllRecords();
        model.addAttribute("records", records);
        return "listRecords";
    }

    @GetMapping("/add")
    public String showAddRecordForm(Model model) {
        model.addAttribute("record", new Record());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "addRecord";
    }

    @PostMapping
    public String saveRecord(@ModelAttribute("record") Record record, @RequestParam("bannerFile") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                Files.write(path, bytes);
                record.setBanner(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        recordService.saveRecord(record);
        // Send email notification
        emailService.sendSimpleMessage("pankajsharma7352@outlook.com", "New Record Added", "A new record has been added: " + record.getTitle());
        return "redirect:/records/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditRecordForm(@PathVariable Long id, Model model) {
        Optional<Record> record = recordService.getRecordById(id);
        model.addAttribute("record", record);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "editRecord";
    }

    @PostMapping("/update")
    public String updateRecord(@ModelAttribute("record") Record record, @RequestParam("bannerFile") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                Files.write(path, bytes);
                record.setBanner(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        recordService.updateRecord(record);
        // Send email notification
        emailService.sendSimpleMessage("pankajsharma7352@outlook.com", "Record Updated", "The record has been updated: " + record.getTitle());
        return "redirect:/records/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
        recordService.deleteRecordById(id);
        // Send email notification
        emailService.sendSimpleMessage("pankajsharma7352@outlook.com", "Record Deleted", "A record has been deleted with ID: " + id);
        return "redirect:/records/list";
    }
}





