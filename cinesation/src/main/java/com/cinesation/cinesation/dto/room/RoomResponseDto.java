package com.cinesation.cinesation.dto.room;

import com.cinesation.cinesation.domain.room.DiscussionRoom;
import java.time.LocalDateTime;

public record RoomResponseDto(
        Long id,
        String title,
        String movieId,
        LocalDateTime createdAt,
        LocalDateTime expiresAt,
        String status
) {
    // Entity를 DTO로 변환
    public static RoomResponseDto from(DiscussionRoom room) {
        return new RoomResponseDto(
                room.getId(),
                room.getTitle(),
                room.getMovieId(),
                room.getCreatedAt(),
                room.getExpires_at(),
                room.getStatus()
        );
    }
}