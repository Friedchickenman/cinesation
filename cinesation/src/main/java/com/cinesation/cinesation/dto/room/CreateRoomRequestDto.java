package com.cinesation.cinesation.dto.room;

// 프론트엔드에서 방 만들 때 보내줄 데이터
public record CreateRoomRequestDto(
        String title,
        String movieId
) {
}