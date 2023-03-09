package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Service.ContentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    @GetMapping("/content/search")
    public Long getLatestContent(@RequestParam String keyword, @RequestParam String mood, @RequestParam String type) {
        return contentService.findLatestByDescriptionAndMoodAndType(keyword, mood, type);
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
