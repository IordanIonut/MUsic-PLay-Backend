package com.example.MUsicPLay.Repository;

import com.example.MUsicPLay.Model.PlaylistContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistContentRepository extends JpaRepository<PlaylistContent,Long> {
    @Query(value = "SELECT * FROM playlist_content WHERE playlist_content_id = :playlist_content_id",nativeQuery = true)
    List<PlaylistContent> findByPlaylistId(@Param("playlist_content_id") Long playlist_content_id);

    @Query(value = "SELECT p.playlist_id, p.user_id, p.fill, p.name, p.mood, c.content_id, c.type, c.description, c.id_page, count(pc.playlist_content_id) AS num_elements " +
            "FROM playlist p INNER JOIN playlist_content pc ON p.playlist_id = pc.playlist_id INNER JOIN content c ON c.content_id = pc.content_id " +
            "WHERE p.mood = :mood AND p.user_id = :user_id GROUP BY p.playlist_id ORDER BY p.playlist_id DESC", nativeQuery = true)
    List<Object[]> getAllByMood(@Param("mood") String mood, @Param("user_id") Long user_id);

    @Query(value = "SELECT pc.*, c.*, p.* FROM playlist_content pc INNER JOIN content c ON pc.content_id = c.content_id INNER JOIN playlist p " +
            "ON p.playlist_id = pc.playlist_id WHERE p.mood = :mood AND p.name = :name AND p.user_id = :user_id and p.playlist_id = :playlist_id  " +
            "GROUP BY pc.playlist_content_id, id_page ORDER BY pc.playlist_content_id ASC", nativeQuery = true)
    List<PlaylistContent> getPlaylistContentById(@Param("mood") String mood, @Param("name") String name, @Param("user_id") Long user_id, @Param("playlist_id") Long playlist_id);

    @Query(value = "SELECT pc.*, c.*, p.* FROM playlist_content pc INNER JOIN content c ON pc.content_id = c.content_id INNER JOIN playlist p " +
            "ON p.playlist_id = pc.playlist_id WHERE p.mood = :mood AND p.name = :name AND p.user_id = :user_id and p.playlist_id = :playlist_id  " +
            "GROUP BY pc.playlist_content_id, id_page ORDER BY pc.playlist_content_id ASC LIMIT 1", nativeQuery = true)
    List<PlaylistContent> getPlaylistContentById2(@Param("mood") String mood, @Param("name") String name, @Param("user_id") Long user_id, @Param("playlist_id") Long playlist_id);
}