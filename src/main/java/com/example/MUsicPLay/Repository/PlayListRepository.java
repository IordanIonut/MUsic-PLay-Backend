package com.example.MUsicPLay.Repository;

import com.example.MUsicPLay.Model.History;
import com.example.MUsicPLay.Model.PlayList;
import com.example.MUsicPLay.Model.PlaylistContent;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepository  extends JpaRepository<PlayList,Long> {
    @Query(value = "SELECT * FROM playlist p inner join playlist_content pc on p.playlist_id = pc.playlist_id inner join content c on pc.content_id = c.content_id\n" +
            "WHERE p.user_id = :user_id and c.mood = :mood group by p.playlist_id order by p.playlist_id desc", nativeQuery = true)
    List<PlayList> getAllPlaylistByUser(@Param("user_id") Long user_id, @Param("mood") String mood);
    @Query(value = "SELECT * FROM playlist p INNER JOIN playlist_content c on p.playlist_id = c.playlist_id INNER JOIN content ct " +
            "on c.content_id = ct.content_id where p.user_id = :user_id and ct.id_page= :id_page and ct.mood = :mood ", nativeQuery = true)
    List<PlayList> getAllPlaylistByUserAndIdPage(@Param("user_id") Long user_id, @Param("id_page") String id_page, @Param("mood") String mood);
    @Query(value = "SELECT c.playlist_content_id, p.playlist_id FROM playlist as p INNER JOIN playlist_content as c on p.playlist_id = c.playlist_id INNER JOIN " +
            "content as ct on c.content_id = ct.content_id where p.user_id = :user_id and ct.id_page= :id_page and ct.mood = :mood and " +
            "p.playlist_id = :plalist_id", nativeQuery = true)
    List<Object[]> getPlayListByPlaylistIdAnAndDelete(@Param("user_id") Long user_id, @Param("id_page") String id_page, @Param("mood") String mood, @Param("plalist_id") Long playlist_id);

    @Query(value = "SELECT * FROM playlist WHERE playlist_id = :playlist_id", nativeQuery = true)
    PlayList getByPlaylistId(@Param("playlist_id") Long playlist_id);

    @Query(value = "SELECT count(pc.playlist_content_id) FROM playlist_content pc where playlist_id = :playlist_id", nativeQuery = true)
    Long getCountByPlaylistContentId(@Param("playlist_id") Long playlist_id);

}