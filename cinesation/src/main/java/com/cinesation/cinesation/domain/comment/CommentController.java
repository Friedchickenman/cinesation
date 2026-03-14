package com.cinesation.cinesation.domain.comment;

import com.cinesation.cinesation.dto.comment.CreateCommentRequestDto;
import com.cinesation.cinesation.dto.comment.CommentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "댓글 API", description = "토론방 댓글 작성 및 조회 기능")
@RestController
@RequestMapping("/api/rooms/{roomId}/comments") // 👈 주소에 roomId가 들어갑니다!
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 1. 댓글 작성 API (POST)
    @Operation(summary = "댓글 작성", description = "특정 토론방에 새로운 댓글을 남깁니다.")
    @PostMapping
    public ResponseEntity<CommentResponseDto> addComment(
            @PathVariable Long roomId, // 👈 주소에 있는 {roomId}를 쏙 빼옵니다.
            @RequestBody CreateCommentRequestDto request) {

        Comment savedComment = commentService.addComment(roomId, request.content(), request.author());
        return ResponseEntity.ok(CommentResponseDto.from(savedComment));
    }

    // 2. 댓글 목록 조회 API (GET)
    @Operation(summary = "댓글 목록 조회", description = "특정 토론방에 달린 모든 댓글을 시간순으로 가져옵니다.")
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long roomId) {

        List<CommentResponseDto> responses = commentService.getCommentsByRoom(roomId).stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}