package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.PlayList;
import com.example.MUsicPLay.Model.PlaylistContent;
import com.example.MUsicPLay.Service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PlayListController {
    @Autowired
    private PlayListService playListService;
    @GetMapping("/playList/playlist_id")
    public PlayList getAllPlayList(@RequestParam Long playlist_id) {
        return playListService.getByPlaylistId(playlist_id);
    }
    @GetMapping("/playList/count")
    public Long getCountByPlaylistContentId(@RequestParam Long playlist_id) {
        return playListService.getCountByPlaylistContentId(playlist_id);
    }
    @GetMapping("/playList/all")
    public List<PlayList> getAllPlayList() {
        return playListService.getAllPlayList();
    }
    @GetMapping("/playList/user_id/id_page")
    public List<PlayList> getAllPlaylistByUserAndIdPage(@RequestParam Long user_id, @RequestParam String id_page, @RequestParam String mood) {
        return playListService.getAllPlaylistByUserAndIdPage(user_id, id_page, mood);
    }
    @GetMapping("/playList/user_id/id_page/playlist_id")
    public List<Object[]> getPlayListByPlaylistIdAnAndDelete(@RequestParam Long user_id, @RequestParam String id_page, @RequestParam String mood, @RequestParam Long playlist_id) {
        return playListService.getPlayListByPlaylistIdAnAndDelete(user_id, id_page, mood, playlist_id);
    }
    @GetMapping("/playList/user_id")
    public List<PlayList> getAllPlayList(@RequestParam Long user_id, @RequestParam String mood) {
        return playListService.getAllPlaylistByUser(user_id, mood);
    }
    @GetMapping("/playList/get/{id}")
    public PlayList getPlayListById(@PathVariable Long id) {
        return playListService.getPlayListById(id);
    }
    @PostMapping("/playList/add")
    public PlayList addPlayList(@RequestBody PlayList user) {
        return playListService.addPlayList(user);
    }
    @PutMapping("/playList/update")
    public PlayList updatePlayList(@RequestBody PlayList user) {
        return playListService.updatePlayList(user);
    }
    @DeleteMapping("/playList/delete/{id}")
    public void deletePlayList(@PathVariable Long id) {
        playListService.deletePlayList(id);
    }
    @GetMapping("/playList/delete")
    public void deletePlayListOrInsert(@RequestParam Long user_id, @RequestParam String id_page, @RequestParam String mood, @RequestParam Long playlist_id, @RequestParam Long content_id) {
        playListService.deletePlayListOrInsert(user_id, id_page, mood, playlist_id, content_id);
    }
}
