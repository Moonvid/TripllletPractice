package com.example.practice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class Posts implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String writer;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @Builder
    public Posts(String title, String content, String writer, Date deletedDate){

        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = new Date();
        this.modifiedDate = new Date();
        this.deletedDate = deletedDate;

    }

}
