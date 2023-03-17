package com.example.MUsicPLay.Service;

import com.example.MUsicPLay.Model.PlayList;
import com.example.MUsicPLay.Model.PlaylistContent;
import com.example.MUsicPLay.Repository.PlaylistContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlaylistContentService {
    @Autowired
    private PlaylistContentRepository playlistContentRepository;
    public List<PlaylistContent> findByPlaylistId(Long playlist_content_id) {
        return playlistContentRepository.findByPlaylistId(playlist_content_id);
    }
    public List<Object[]> getAllByMood(String mood, Long user_id) {
        return playlistContentRepository.getAllByMood(mood, user_id);
    }
    public List<PlaylistContent> getPlaylistContentById(String mood, String name, Long user_id, Long playlist_id) {
        return playlistContentRepository.getPlaylistContentById(mood, name, user_id, playlist_id);
    }
    public List<PlaylistContent> getPlaylistContentById2(String mood, String name, Long user_id, Long playlist_id) {
        return playlistContentRepository.getPlaylistContentById2(mood, name, user_id, playlist_id);
    }
    public List<PlaylistContent> getAllPlaylistContent() {
        return playlistContentRepository.findAll();
    }
    public PlaylistContent getPlaylistContentById(Long id) {
        return playlistContentRepository.findById(id).orElse(null);
    }
    public PlaylistContent addPlaylistContent(PlaylistContent user) {
        return playlistContentRepository.save(user);
    }
    public PlaylistContent updatePlaylistContent(PlaylistContent user) {
        PlaylistContent existingUser = playlistContentRepository.findById(user.getPlaylist_content_id()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        return playlistContentRepository.save(user);
    }
    public void deletePlaylistContent(Long id) {
        playlistContentRepository.deleteById(id);
    }
}
