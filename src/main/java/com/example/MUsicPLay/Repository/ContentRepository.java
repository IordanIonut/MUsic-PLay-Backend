package com.example.MUsicPLay.Repository;

import com.example.MUsicPLay.Model.Content;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {
    @Query(value = "SELECT * FROM Content ORDER BY content_id DESC LIMIT 1", nativeQuery = true)
    Content findLatest();
    Optional<Content> findByDescriptionAndMoodAndType(JsonNode description, String mood, String type);
}