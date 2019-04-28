package com.example.practice.etc;

import com.example.practice.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class PostsMainResponseDto {
    private Long id;
    private String title;
    private String writer;
    private String modifiedDate;

    /*
    public PostsMainResponseDto(Posts entity) {
        id = entity.getId();
        title = entity.getTitle();
        writer = entity.getWriter();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }


    private String toStringDateTime(LocalDateTime localDateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }


    //    Java 7 버전
    /*
    private String toStringDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(localDateTime);
    }
    */
}


