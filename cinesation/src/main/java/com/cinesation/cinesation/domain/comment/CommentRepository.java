package com.cinesation.cinesation.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 💡 마법의 메서드: 특정 토론방(room_id)에 달린 모든 댓글을 작성 시간순으로 가져옵니다!
    List<Comment> findAllByRoomIdOrderByCreatedAtAsc(Long roomId);
}