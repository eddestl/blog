package com.smallblog.blog.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    public Post(Long id, String title, String text, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Post() {
    }

}
