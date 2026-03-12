package com.cinesation.cinesation.domain.room;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional // 테스트가 끝나면 DB에 넣은 데이터를 흔적도 없이 롤백(삭제)해 줍니다.
class DiscussionRoomServiceTest {

    @Autowired
    DiscussionRoomService discussionRoomService;

    @Autowired
    DiscussionRoomRepository roomRepository;

    @Test
    @DisplayName("토론방이 정상적으로 생성되고, 만료일이 7일 뒤로 설정된다.")
    void createRoom_success() {
        // given (준비)
        String title = "인셉션 토론방";
        String movieId = "m_12345";

        // when (실행)
        DiscussionRoom createdRoom = discussionRoomService.createRoom(title, movieId);

        // then (검증)
        assertThat(createdRoom.getId()).isNotNull();
        assertThat(createdRoom.getTitle()).isEqualTo(title);
        assertThat(createdRoom.getStatus()).isEqualTo("OPEN");

        // 7일(약간의 오차 허용을 위해 6일 이후인지) 뒤로 잘 설정되었는지 확인
        assertThat(createdRoom.getExpires_at()).isAfter(LocalDateTime.now().plusDays(6));
    }

    @Test
    @DisplayName("이미 열려있는 같은 영화의 방이 있으면 예외가 발생한다.")
    void createRoom_duplicate_throwsException() {
        // given (미리 방을 하나 만들어 둠)
        String title = "다크나이트 토론방";
        String movieId = "m_99999";
        discussionRoomService.createRoom(title, movieId); // 첫 번째 방 생성

        // when & then (같은 영화 ID로 또 만들려고 하면 우리가 설정한 예외가 터져야 성공!)
        assertThatThrownBy(() -> discussionRoomService.createRoom("다크나이트 또 다른 방", movieId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 진행 중인 해당 영화의 토론방이 존재합니다.");
    }
}