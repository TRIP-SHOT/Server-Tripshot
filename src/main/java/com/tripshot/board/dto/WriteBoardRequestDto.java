package com.tripshot.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class WriteBoardRequestDto {
    private Long id;//pk
    private Long userId;//user의 고유id
    private String title;//제목
    private String createDate;//생성시간
    private String weather;//날씨
    private String season;//계절
    private String shotDate;//사진찍은날
    private String content;//내용
    private String spot;//장소
    private String locationName;//장소명
    private int hit;//조회수
    private int heartCount;//좋아요수
    private double longitude;//경도
    private double latitude;//위도
    private MultipartFile image;//사진

    public Board toBoard() {
        Board board = Board.builder()
                .id(id)
                .userId(userId)
                .title(title).createDate(createDate).weather(weather).season(season).shotDate(shotDate)
                .content(content).spot(spot).hit(hit).heartCount(heartCount).longitude(longitude).latitude(latitude).locationName(locationName)
                .build();
        return board;
    }
}


