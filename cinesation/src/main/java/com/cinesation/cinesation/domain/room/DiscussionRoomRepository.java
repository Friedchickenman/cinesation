package com.cinesation.cinesation.domain.room;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiscussionRoomRepository extends JpaRepository<DiscussionRoom, Long> {

    // 상태가 'OPEN'인 방만 찾아서 가져오는 기능 (나중에 목록 보여줄 때 쓰겠죠?)
    List<DiscussionRoom> findAllByStatus(String status);

    // 특정 영화 ID로 생성된 방이 있는지 찾는 기능
    List<DiscussionRoom> findAllByMovieId(String movieId);
}