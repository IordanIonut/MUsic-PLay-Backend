package com.example.MUsicPLay.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "playlist_content")
public class PlaylistContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_content_id")
    private Long playlist_content_id;
    @ManyToOne
    @JoinColumn(name = "content_id",referencedColumnName = "content_id")
    private Content content_id;
    @ManyToOne
    @JoinColumn(name = "playlist_id",referencedColumnName = "playlist_id")
    private PlayList playlist_id;
    public PlaylistContent(Content content_id, PlayList playlist_id){
        this.content_id = content_id;
        this.playlist_id = playlist_id;
    }
    public PlaylistContent() {
    }
}
