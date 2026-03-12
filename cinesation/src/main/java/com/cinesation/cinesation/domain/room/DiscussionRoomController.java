package com.cinesation.cinesation.domain.room;

import com.cinesation.cinesation.dto.room.CreateRoomRequestDto;
import com.cinesation.cinesation.dto.room.RoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class DiscussionRoomController {

    private final DiscussionRoomService roomService;

    // 1. 방 생성 API (POST)
    @PostMapping
    public ResponseEntity<RoomResponseDto> createRoom(@RequestBody CreateRoomRequestDto request) {
        DiscussionRoom savedRoom = roomService.createRoom(request.title(), request.movieId());
        return ResponseEntity.ok(RoomResponseDto.from(savedRoom));
    }

    // 2. 열려있는 방 목록 조회 API (GET)
    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> getOpenRooms() {
        List<RoomResponseDto> responses = roomService.getOpenRooms().stream()
                .map(RoomResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}