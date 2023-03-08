package com.example.MUsicPLay.Repository;

import com.example.MUsicPLay.Model.History;
import com.example.MUsicPLay.Model.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
    Optional<History> findByUserIdAndContent_DescriptionAndContent_MoodAndContent_Type(User userId, JsonNode description, String mood, String type);
}