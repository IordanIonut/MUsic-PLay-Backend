package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.PlayList;
import com.example.MUsicPLay.Model.User;
import com.example.MUsicPLay.Service.PlayListService;
import com.example.MUsicPLay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PlayListController {
    @Autowired
    private PlayListService playListService;
    @GetMapping("/playList/all")
    public List<PlayList> getAllPlayList() {
        return playListService.getAllPlayList();
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
}
