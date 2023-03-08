package com.example.MUsicPLay.Controller;

import java.util.Optional;
import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Service.ContentService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ContentController {
    @Autowired
    private ContentService contentService;
    @GetMapping("/content/last")
    public ResponseEntity<Content> findLast() {
        Content entity = contentService.getLatestContent();
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }
    @GetMapping("/content/content")
    public ResponseEntity<Content> getContentByDescriptionAndMoodAndType(@RequestParam JsonNode description, @RequestParam String mood, @RequestParam String type) {
        Optional<Content> content = contentService.getContentByDescriptionAndMoodAndType(description, mood, type);
        if (content.isPresent()) {
            return ResponseEntity.ok(content.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/content/all")
    public List<Content> getAllContent() {
        return contentService.getAllContent();
    }
    @GetMapping("/content/get/{id}")
    public Content getContentById(@PathVariable Long id) {
        return contentService.getContentById(id);
    }
    @PostMapping("/content/add")
    public Content addContent(@RequestBody Content user) {
        LocalDateTime  loc =  LocalDateTime.now();
        user.setDate(loc);
        Content savedContent = contentService.addContent(user);
        return new ResponseEntity<>(savedContent, HttpStatus.CREATED).getBody();
    }
    @PutMapping("/content/update")
    public Content updateContent(@RequestBody Content user) {
        return contentService.updateContent(user);
    }
    @DeleteMapping("/content/delete/{id}")
    public void deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
    }
}
