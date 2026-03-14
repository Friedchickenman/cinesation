package com.cinesation.cinesation.domain.comment;

import com.cinesation.cinesation.domain.room.DiscussionRoom;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 객체 생성 방지
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 💡 Pro 팁: 댓글(N)은 항상 하나의 토론방(1)에 속합니다. (N:1 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private DiscussionRoom room;

    @Column(nullable = false, length = 500) // 댓글은 최대 500자로 제한
    private String content;

    @Column(nullable = false)
    private String author; // 나중에 '회원' 기능이 생기면 바꾸겠지만, 지금은 작성자 닉네임으로 씁니다!

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // 생성자 (댓글을 처음 달 때 사용)
    public Comment(DiscussionRoom room, String content, String author) {
        this.room = room;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }
}