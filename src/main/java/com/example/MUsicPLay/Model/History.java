package com.example.MUsicPLay.Model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long history_id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user_id;
    @ManyToOne
    @JoinColumn(name = "content_id",referencedColumnName = "content_id")
    private Content content_id;
    public History() {
    }
}
