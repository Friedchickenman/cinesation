package com.cinesation.cinesation.domain.room;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import java.time.LocalDateTime;

@Entity
@Table(name = "discussion_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 객체 생성 방지
public class DiscussionRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "movie_id", nullable = false)
    private String movieId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expires_at;

    @Column
    private String status = "OPEN";

    // 생성자 (방을 처음 만들 때 사용)
    public DiscussionRoom(String title, String movieId) {
        this.title = title;
        this.movieId = movieId;
        this.createdAt = LocalDateTime.now();
        this.expires_at = this.createdAt.plusDays(7); // 7일간의 기록 컨셉!
        this.status = "OPEN";
    }
}