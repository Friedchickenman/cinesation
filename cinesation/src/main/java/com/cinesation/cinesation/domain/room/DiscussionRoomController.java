package com.cinesation.cinesation.domain.room;

import com.cinesation.cinesation.dto.room.CreateRoomRequestDto;
import com.cinesation.cinesation.dto.room.RoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "토론방 API", description = "토론방 생성 및 조회 기능") // 👈 클래스 위에 추가
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class DiscussionRoomController {

    private final DiscussionRoomService roomService;

    // 1. 방 생성 API (POST)
    @Operation(summary = "새로운 토론방 생성", description = "영화 ID와 제목을 받아 7일짜리 토론방을 만듭니다.") // 👈 여기에 추가!
    @PostMapping
    public ResponseEntity<RoomResponseDto> createRoom(@RequestBody CreateRoomRequestDto request) {
        DiscussionRoom savedRoom = roomService.createRoom(request.title(), request.movieId());
        return ResponseEntity.ok(RoomResponseDto.from(savedRoom));
    }

    // 2. 열려있는 방 목록 조회 API (GET)
    @Operation(summary = "진행 중인 토론방 목록 조회", description = "현재 상태가 OPEN인 모든 토론방 목록을 가져옵니다.") // 👈 여기에 추가!
    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> getOpenRooms() {
        List<RoomResponseDto> responses = roomService.getOpenRooms().stream()
                .map(RoomResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }



}