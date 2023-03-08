package com.example.MUsicPLay.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long favorite_id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user_id;
    @Column(name = "fill")
    private String fill;
    @ManyToOne
    @JoinColumn(name = "content_id",referencedColumnName = "content_id")
    private Content content_id;
    public Favorite(){
    }
}
