package com.example.MUsicPLay.Service;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.User;
import com.example.MUsicPLay.Repository.ContentRepository;
import com.example.MUsicPLay.Repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;
    public Content getLatestContent() {
        return contentRepository.findLatest();
    }
    public Optional<Content> getContentByDescriptionAndMoodAndType(JsonNode description, String mood, String type) {
        return contentRepository.findByDescriptionAndMoodAndType(description, mood, type);
    }
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }
    public Content getContentById(Long id) {
        return contentRepository.findById(id).orElse(null);
    }
    public Content addContent(Content user) {
        return contentRepository.save(user);
    }
    public Content updateContent(Content user) {
        Content existingUser = contentRepository.findById(user.getContent_id()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        return contentRepository.save(user);
    }
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }
}
