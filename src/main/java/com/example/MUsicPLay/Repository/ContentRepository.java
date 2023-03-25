package com.example.MUsicPLay.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.example.MUsicPLay.Model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {
    @Query(value = "SELECT * FROM Content ORDER BY content_id DESC LIMIT 1", nativeQuery = true)
    Content findLatest();
    @Query(value = "SELECT * FROM Content c WHERE c.description like %:keyword% AND c.mood = :mood AND c.type = :type ORDER BY c.content_id DESC LIMIT 2", nativeQuery = true)
    List<Content> findLatestByDescriptionAndMoodAndType(@Param("keyword") String keyword, @Param("mood") String mood, @Param("type") String type);

    @Query(value = "SELECT * FROM Content c WHERE c.id_page = :id_page", nativeQuery = true)
    List<Content> findByIdPage(@Param("id_page") String id_page);

    @Query(value = "SELECT * FROM Content c WHERE c.content_id = :content_id", nativeQuery = true)
    Content findByContentId(@Param("content_id") Long id_page);
}
