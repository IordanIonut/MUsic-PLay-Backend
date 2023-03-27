package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.History;
import com.example.MUsicPLay.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @GetMapping("history/unused-content")
    public List<Object[]> findContentNotUsed() {
        return historyService.findContentNotUsed();
    }
    @PostMapping("history/save")
    public History insertOrUpdateHistory(@RequestParam Long userId, @RequestParam String mode, @RequestParam String type, @RequestParam String description) {
      return  historyService.insertOrUpdateHistory(userId, mode, type, description);
    }
    @GetMapping("/history/search")
    public History getLatestContent(@RequestParam Long user_id, @RequestParam Long content_id) {
        return historyService.findHistoryByUserAndContent(user_id, content_id);
    }
    @GetMapping("/history/search/all")
    public List<History> findAllCollomFromContentAndHistoryOrderByDate(@RequestParam Long user_id, @RequestParam String type) {
        return historyService.findAllCollomFromContentAndHistoryOrderByDate(user_id, type);
    }
    @GetMapping("/history/all")
    public List<History> getAllHistory() {
        return historyService.getAllHistory();
    }
    @GetMapping("/history/get/{id}")
    public History getHistoryById(@PathVariable Long id) {
        return historyService.getHistoryById(id);
    }
    @PostMapping("/history/add")
    public History addHistory(@RequestBody History user) {
        History savedContent = historyService.addHistory(user);
        return new ResponseEntity<>(savedContent, HttpStatus.CREATED).getBody();
    }
    @PutMapping("/history/update")
    public History updateHistory(@RequestBody History user) {
        return historyService.updateHistory(user);
    }
    @DeleteMapping("/history/delete/{id}")
    public void deleteHistory(@PathVariable Long id) {
        historyService.deleteHistory(id);
    }
}
