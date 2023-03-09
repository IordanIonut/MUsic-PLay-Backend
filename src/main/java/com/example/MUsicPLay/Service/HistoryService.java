package com.example.MUsicPLay.Service;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.History;
import com.example.MUsicPLay.Model.User;
import com.example.MUsicPLay.Repository.ContentRepository;
import com.example.MUsicPLay.Repository.HistoryRepository;
import com.example.MUsicPLay.Repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private ContentRepository contentRepository;
    public History insertOrUpdateHistory(Long userId, String mode, String type, String description) {
        User user = new User();
        user.setUser_id(userId);
        List<Content> contentList = contentRepository.findLatestByDescriptionAndMoodAndType(description, mode, type);
        History history = null;
        if (contentList.size() > 0) {
            Content content = contentList.get(contentList.size() - 1);
            history = historyRepository.findByUserAndContent(user.getUser_id(), content.getContent_id());
        }
        if (history == null) {
            history = new History();
            history.setUser_id(user);
            history.setContent_id(contentList.get(0));
            return historyRepository.save(history);
        } else {
            Content content = contentList.get(0);
            history.setContent_id(content);
            return historyRepository.save(history);
        }
    }
    public History findByUserAndContent(Long user_id, Long content_id) {
        return historyRepository.findByUserAndContent(user_id, content_id);
    }
    public List<History> findAllCollomFromContentAndHistoryOrderByDate(Long user_id, String mood, String type) {
        return historyRepository.findAllCollomFromContentAndHistoryOrderByDate(user_id, mood, type);
    }
    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }
    public History getHistoryById(Long id) {
        return historyRepository.findById(id).orElse(null);
    }
    public History addHistory(History user) {
        return historyRepository.save(user);
    }
    public History updateHistory(History user) {
        History existingUser = historyRepository.findById(user.getHistory_id()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        return historyRepository.save(user);
    }
    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }
}
