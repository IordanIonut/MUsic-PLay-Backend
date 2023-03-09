package com.example.MUsicPLay.Repository;
import org.springframework.data.repository.query.Param;
import com.example.MUsicPLay.Model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {
    @Query(value = "SELECT * FROM Content ORDER BY content_id DESC LIMIT 1", nativeQuery = true)
    Content findLatest();
    @Query(value = "SELECT c.content_id FROM Content c WHERE c.description like %:keyword% AND c.mood = :mood AND c.type = :type ORDER BY c.content_id DESC LIMIT 1", nativeQuery = true)
    Long findLatestByDescriptionAndMoodAndType(@Param("keyword") String keyword, @Param("mood") String mood, @Param("type") String type);
}
