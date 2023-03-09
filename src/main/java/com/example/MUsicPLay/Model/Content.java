package com.example.MUsicPLay.Model;

import com.example.MUsicPLay.JsonNodeConverter;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long content_id;
    @Column(name = "mood")
    private String mood;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "description", length = 10000)
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode description;
    @Column(name = "type")
    private String type;
}
