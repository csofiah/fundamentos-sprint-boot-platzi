package com.fundamentosplatzi.sprintboot.fundamentos.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false, unique = true)
    private Long id;

    @Column(name="description", length = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id") //No necesario para el ejemplo del curso pero se puede explicar
    private User user;

    public Post() {
    }

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }


}
