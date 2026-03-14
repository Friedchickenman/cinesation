package com.cinesation.cinesation.domain.comment;

import com.cinesation.cinesation.domain.room.DiscussionRoom;
import com.cinesation.cinesation.domain.room.DiscussionRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final DiscussionRoomRepository roomRepository; // 👈 방이 진짜 있는지 확인하려고 데려옵니다.

    /**
     * 댓글 달기 (비즈니스 로직)
     */
    @Transactional
    public Comment addComment(Long roomId, String content, String author) {
        // 1. 방이 존재하는지 확인합니다. 없으면 에러 팡!
        DiscussionRoom room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 토론방입니다."));

        // 2. 방이 열려있는지(OPEN) 확인합니다. 닫혔으면 에러 팡!
        if (!"OPEN".equals(room.getStatus())) {
            throw new IllegalStateException("이미 종료된 토론방에는 댓글을 달 수 없습니다.");
        }

        // 3. 검증이 다 끝났으니 안전하게 댓글을 만들고 저장합니다.
        Comment comment = new Comment(room, content, author);
        return commentRepository.save(comment);
    }

    /**
     * 특정 방의 댓글 목록 가져오기
     */
    public List<Comment> getCommentsByRoom(Long roomId) {
        // Repository에 우리가 아까 만들어둔 그 마법의 메서드를 씁니다!
        return commentRepository.findAllByRoomIdOrderByCreatedAtAsc(roomId);
    }
}