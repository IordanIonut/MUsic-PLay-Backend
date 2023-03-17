package com.example.MUsicPLay.Service;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Repository.ContentRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;
    public Content getLatestContent() {
        return contentRepository.findLatest();
    }
    public  List<Content> findLatestByDescriptionAndMoodAndType(String keyword, String mood, String type) {
        return contentRepository.findLatestByDescriptionAndMoodAndType(keyword, mood, type);
    }
    public  List<Content> findByIdPage(String id_page) {
        return contentRepository.findByIdPage(id_page);
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
