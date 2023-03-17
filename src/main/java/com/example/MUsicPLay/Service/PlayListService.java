package com.example.MUsicPLay.Service;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.PlayList;
import com.example.MUsicPLay.Model.PlaylistContent;
import com.example.MUsicPLay.Model.User;
import com.example.MUsicPLay.Repository.ContentRepository;
import com.example.MUsicPLay.Repository.PlayListRepository;
import com.example.MUsicPLay.Repository.PlaylistContentRepository;
import com.example.MUsicPLay.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class PlayListService {
    @Autowired
    private PlayListRepository playListRepository;
    @Autowired
    private PlaylistContentRepository playlistContentRepository;
    @Autowired
    private ContentRepository contentRepository;
    public List<PlayList> getAllPlaylistByUser(Long user_id, String mood) {
        return playListRepository.getAllPlaylistByUser(user_id, mood);
    }
    public Long getCountByPlaylistContentId(Long playlist_id) {
        return playListRepository.getCountByPlaylistContentId(playlist_id);
    }
    public PlayList getByPlaylistId(Long playlist_id) {
        return playListRepository.getByPlaylistId(playlist_id);
    }
    public List<PlayList> getAllPlaylistByUserAndIdPage(Long user_id, String id_page, String mood) {
        return playListRepository.getAllPlaylistByUserAndIdPage(user_id, id_page, mood);
    }
    public List<Object[]> getPlayListByPlaylistIdAnAndDelete(Long user_id, String id_page, String mood, Long playlist_id) {
        return playListRepository.getPlayListByPlaylistIdAnAndDelete(user_id, id_page, mood, playlist_id);
    }
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
    public void deletePlayListOrInsert(Long user_id, String id_page, String mood, Long playlist_id, Long content_id) {
        List<Object[]> playList = playListRepository.getPlayListByPlaylistIdAnAndDelete(user_id, id_page, mood, playlist_id);
        if(playList.size() == 0){
            System.out.println("playlist este empty");
            PlayList pl = playListRepository.getByPlaylistId(playlist_id);
            Content con = contentRepository.findByContentId(content_id);
            PlaylistContent playlistContent = new PlaylistContent(con, pl);
            playlistContentRepository.save(playlistContent);
        }
        else{
            System.out.println("playlist nu este empty");
            Long pla = playListRepository.getCountByPlaylistContentId(playlist_id);
            System.out.println(playList.get(0)[0]+"      "+playList.get(0)[1]);
            playlistContentRepository.deleteById(((Number) playList.get(0)[0]).longValue());
            if(pla == 1)
                playListRepository.deleteById(((Number) playList.get(0)[1]).longValue());
        }
    }
}
