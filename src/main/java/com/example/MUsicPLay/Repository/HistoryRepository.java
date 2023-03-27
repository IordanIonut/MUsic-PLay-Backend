package com.example.MUsicPLay.Repository;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.Favorite;
import com.example.MUsicPLay.Model.History;
import com.example.MUsicPLay.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
    @Query(value = "SELECT * FROM History h WHERE h.user_id = :user_id AND h.content_id = :content_id", nativeQuery = true)
    History findHistoryByUserAndContent(@Param("user_id") Long user_id, @Param("content_id") Long content_id);

    @Query(value = "SELECT a.history_id, a.user_id, b.content_id, b.date, b.description, b.mood, b.type , b.id_page " +
            "FROM history  a inner  join content  b on a.content_id = b.content_id" +
            "    where a.user_id = :user_id  AND b.type = :type order by date desc", nativeQuery = true)
    List<History> findAllCollomFromContentAndHistoryOrderByDate(@Param("user_id") Long user_id, @Param("type") String type);

    @Query(value = "SELECT c.* FROM content c WHERE NOT EXISTS (" +
            "SELECT 1 FROM history h WHERE h.content_id = c.content_id UNION " +
            "SELECT 1 FROM favorite f WHERE f.content_id = c.content_id UNION " +
            "SELECT 1 FROM playlist_content pc WHERE pc.content_id = c.content_id)", nativeQuery = true)
    List<Object[]> findContentNotUsed();
    @Modifying
    @Query(value = "Select * FROM History h INNER JOIN content c ON c.content_id = h.content_id WHERE c.id_page = :id_page " +
            "AND h.user_id = :user_id AND h.history_id NOT IN ( SELECT MAX(history_id) FROM History WHERE user_id = :user_id AND content_id IN ( " +
            "SELECT content_id FROM content WHERE id_page = :id_page))", nativeQuery = true)
    List<History> deleteHistoryByIdPageAndUserId(@Param("user_id")Long user_id, @Param("id_page") String id_page);

}