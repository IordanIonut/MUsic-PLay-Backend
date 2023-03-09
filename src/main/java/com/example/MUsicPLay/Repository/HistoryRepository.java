package com.example.MUsicPLay.Repository;

import com.example.MUsicPLay.Model.History;
import com.example.MUsicPLay.Model.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
    @Query(value = "SELECT * FROM History h WHERE h.user_id = :user_id AND h.content_id = :content_id", nativeQuery = true)
    History findByUserAndContent(@Param("user_id") Long user_id, @Param("content_id") Long content_id);
}