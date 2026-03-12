package com.cinesation.cinesation.domain.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j // 로그를 찍기 위한 어노테이션
@Component
@RequiredArgsConstructor
public class DiscussionRoomScheduler {

    private final DiscussionRoomRepository roomRepository;

    // 💡 Pro 팁: 매 정각마다 실행하거나, 10분마다 실행하도록 설정 (여기서는 테스트를 위해 1분마다 실행)
    // 실무에서는 "0 0 * * * *" (매 정각) 등으로 설정합니다.
    @Scheduled(cron = "0 * * * * *") // 매 1분마다 실행
    @Transactional
    public void closeExpiredRooms() {
        log.info("[Scheduler] 만료된 토론방 종료 작업 시작...");

        // 1. 현재 시간 기준으로 만료되었는데 아직 'OPEN'인 방들을 다 찾습니다.
        // (주의: Repository에 이 메서드를 추가해야 합니다. 아래 4단계 참고!)
        List<DiscussionRoom> expiredRooms = roomRepository.findExpiredRooms(LocalDateTime.now(), "OPEN");

        // 2. 찾은 방들의 상태를 'CLOSED'로 변경합니다.
        for (DiscussionRoom room : expiredRooms) {
            room.closeRoom();
            log.info("토론방 종료됨: 방 번호={}, 영화 ID={}", room.getId(), room.getMovieId());
        }

        log.info("[Scheduler] 만료된 토론방 종료 작업 완료. 총 {}개 종료.", expiredRooms.size());
    }
}