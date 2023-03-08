package com.example.MUsicPLay.Service;

import com.example.MUsicPLay.Model.PlayList;
import com.example.MUsicPLay.Model.User;
import com.example.MUsicPLay.Repository.PlayListRepository;
import com.example.MUsicPLay.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListService {
    @Autowired
    private PlayListRepository playListRepository;
    public List<PlayList> getAllPlayList() {
        return playListRepository.findAll();
    }
    public PlayList getPlayListById(Long id) {
        return playListRepository.findById(id).orElse(null);
    }
    public PlayList addPlayList(PlayList user) {
        return playListRepository.save(user);
    }
    public PlayList updatePlayList(PlayList user) {
        PlayList existingUser = playListRepository.findById(user.getPlaylist_id()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        return playListRepository.save(user);
    }
    public void deletePlayList(Long id) {
        playListRepository.deleteById(id);
    }
}
