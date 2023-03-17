package com.example.MUsicPLay.Repository;

import com.example.MUsicPLay.Model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    @Query(value = "SELECT DISTINCT c.id_page, c.*, f.* FROM favorite f INNER JOIN content c ON c.content_id = f.content_id AND c.type = :type" +
            " AND c.mood = :mood LEFT JOIN content c2 ON c.id_page = c2.id_page AND c.date < c2.date WHERE f.user_id = :user_id AND c2.date " +
            "IS NULL GROUP BY f.user_id, f.content_id ORDER BY c.date DESC", nativeQuery = true)
    List<Favorite> findHistory(@Param("user_id") Long user_id, @Param("type") String type, @Param("mood") String mood);
    @Query(value = "Select f.* FROM Favorite f WHERE f.user_id = :userId AND f.content_id IN (SELECT c.content_id FROM Content c WHERE c.id_page = :idPage)", nativeQuery = true)
    List<Favorite>  findByUserIdAndIdPage(@Param("userId") Long  userId, @Param("idPage") String idPage);

    @Query(value = "SELECT * FROM Favorite h WHERE h.user_id = :user_id AND h.content_id = :content_id", nativeQuery = true)
    Favorite findFavoriteByUserAndContent(@Param("user_id") Long user_id, @Param("content_id") Long content_id);

    @Query(value = "SELECT * FROM Favorite h WHERE h.user_id = :user_id", nativeQuery = true)
    List<Favorite> findFavoriteByFavoriteID(@Param("user_id") Long user_id);
}