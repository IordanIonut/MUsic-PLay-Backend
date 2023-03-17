package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.PlayList;
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
    @GetMapping("/playlistContent/mood/user_id")
    public List<Object[]> getAllByMood(@RequestParam String mood, @RequestParam Long user_id) {
        return playlistContentService.getAllByMood(mood, user_id);
    }
    @GetMapping("/playlistContent/mood/name/user_id/playlist_id")
    public List<PlaylistContent> getPlaylistContentById(@RequestParam String mood, @RequestParam String name, @RequestParam Long user_id, @RequestParam Long playlist_id) {
        return playlistContentService.getPlaylistContentById(mood, name, user_id, playlist_id);
    }
    @GetMapping("/playlistContent/mood/name/user_id/playlist_id2")
    public List<PlaylistContent> getPlaylistContentById2(@RequestParam String mood, @RequestParam String name, @RequestParam Long user_id, @RequestParam Long playlist_id) {
        return playlistContentService.getPlaylistContentById2(mood, name, user_id, playlist_id);
    }
    @GetMapping("/playlistContent/all")
    public List<PlaylistContent> getAllPlaylistContent() {
        return playlistContentService.getAllPlaylistContent();
    }
    @GetMapping("/playlistContent/id")
    public List<PlaylistContent> findByPlaylistId(@RequestParam Long playlist_content_id) {
        return playlistContentService.findByPlaylistId(playlist_content_id);
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
