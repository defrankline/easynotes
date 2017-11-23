package com.frank.api.controller;

import com.frank.api.model.NoteCategory;
import com.frank.api.repository.NoteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteCategoryController {

    @Autowired
    NoteCategoryRepository noteCategoryRepository;

    // Get All NoteCategories
    @GetMapping("/note-categories")
    public List<NoteCategory> getAllNoteCategories() {
        return noteCategoryRepository.findAll();
    }

    // Create a new NoteCategory
    @PostMapping("/note-categories")
    public NoteCategory createNoteCategory(@Valid @RequestBody NoteCategory noteCategory) {
        return noteCategoryRepository.save(noteCategory);
    }

    // Get a Single NoteCategory
    @GetMapping("/note-categories/{id}")
    public ResponseEntity<NoteCategory> getNoteCategoryById(@PathVariable(value = "id") Long noteCategoryId) {
        NoteCategory noteCategory = noteCategoryRepository.findOne(noteCategoryId);
        if(noteCategory == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(noteCategory);
    }

    // Update a NoteCategory
    @PutMapping("/note-categories/{id}")
    public ResponseEntity<NoteCategory> updateNoteCategory(@PathVariable(value = "id") Long noteCategoryId, @Valid @RequestBody NoteCategory noteCategoryDetails) {
        NoteCategory noteCategory = noteCategoryRepository.findOne(noteCategoryId);
        if(noteCategory == null) {
            return ResponseEntity.notFound().build();
        }
        noteCategory.setTitle(noteCategoryDetails.getTitle());

        NoteCategory updatedNoteCategory = noteCategoryRepository.save(noteCategory);
        return ResponseEntity.ok(updatedNoteCategory);
    }

    // Delete a NoteCategory
    @DeleteMapping("/note-categories/{id}")
    public ResponseEntity<NoteCategory> deleteNoteCategory(@PathVariable(value = "id") Long noteCategoryId) {
        NoteCategory noteCategory = noteCategoryRepository.findOne(noteCategoryId);
        if(noteCategory == null) {
            return ResponseEntity.notFound().build();
        }

        noteCategoryRepository.delete(noteCategory);
        return ResponseEntity.ok().build();
    }
}