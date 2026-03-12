package com.cinesation.cinesation.domain.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 💡 Pro 팁: 기본값을 읽기 전용으로 두면 조회 성능이 최적화됩니다.
public class DiscussionRoomService {

    private final DiscussionRoomRepository roomRepository;

    /**
     * 토론방 생성
     */
    @Transactional // 💡 여기는 데이터를 저장(쓰기)해야 하므로 기본값을 덮어씌웁니다.
    public DiscussionRoom createRoom(String title, String movieId) {

        // 💡 Pro 비즈니스 로직: 이미 진행 중인 같은 영화의 방이 있는지 검증
        List<DiscussionRoom> existingRooms = roomRepository.findAllByMovieId(movieId);
        boolean hasOpenRoom = existingRooms.stream()
                .anyMatch(room -> "OPEN".equals(room.getStatus())
                        && room.getExpires_at().isAfter(LocalDateTime.now()));

        if (hasOpenRoom) {
            // 나중에는 이 부분을 우리가 직접 만든 Custom Exception으로 바꿀 겁니다!
            throw new IllegalStateException("이미 진행 중인 해당 영화의 토론방이 존재합니다.");
        }

        DiscussionRoom newRoom = new DiscussionRoom(title, movieId);
        return roomRepository.save(newRoom);
    }

    /**
     * 열려있는 전체 방 조회
     */
    public List<DiscussionRoom> getOpenRooms() {
        return roomRepository.findAllByStatus("OPEN");
    }
}