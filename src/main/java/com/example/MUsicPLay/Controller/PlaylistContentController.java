package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.PlaylistContent;
import com.example.MUsicPLay.Service.ContentService;
import com.example.MUsicPLay.Service.PlaylistContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PlaylistContentController {
    @Autowired
    private PlaylistContentService playlistContentService;
    @GetMapping("/playlistContent/all")
    public List<PlaylistContent> getAllPlaylistContent() {
        return playlistContentService.getAllPlaylistContent();
    }
    @GetMapping("/playlistContent/get/{id}")
    public PlaylistContent getPlaylistContentById(@PathVariable Long id) {
        return playlistContentService.getPlaylistContentById(id);
    }
    @PostMapping("/playlistContent/add")
    public PlaylistContent addPlaylistContent(@RequestBody PlaylistContent user) {
        PlaylistContent savedContent = playlistContentService.addPlaylistContent(user);
        return new ResponseEntity<>(savedContent, HttpStatus.CREATED).getBody();
    }
    @PutMapping("/playlistContent/update")
    public PlaylistContent updatePlaylistContent(@RequestBody PlaylistContent user) {
        return playlistContentService.updatePlaylistContent(user);
    }
    @DeleteMapping("/playlistContent/delete/{id}")
    public void deletePlaylistContent(@PathVariable Long id) {
        playlistContentService.deletePlaylistContent(id);
    }
}
