package com.cinesation.cinesation.dto.comment;

public record CreateCommentRequestDto(
        String content,
        String author
) {
}