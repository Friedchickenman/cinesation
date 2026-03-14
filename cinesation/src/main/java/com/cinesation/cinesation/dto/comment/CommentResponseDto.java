package com.cinesation.cinesation.dto.comment;

import com.cinesation.cinesation.domain.comment.Comment;
import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        String content,
        String author,
        LocalDateTime createdAt
) {
    // Entity를 DTO로 변환해주는 마법의 메서드
    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getCreatedAt()
        );
    }
}