package com.example.MUsicPLay.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "playlist")
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long playlist_id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user_id;
    @Column(name = "fill")
    private String fill;
    @Column(name = "name")
    private String name;
    @Column(name = "mood")
    private String mood;
    public PlayList(){
    }
}
