package com.tripshot.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Board {
    private Long id;//pk
    private Long userId;//user테이블의 user id
    private String title;//제목
    private String createDate;//생성시간
    private String weather;//날씨
    private String season;//계절
    private String shotDate;//사진찍은날
    private String image;//사진
    private String imageKey;//s3에서 이미지를 삭제할때 사용하는 key
    private String content;//내용
    private String spot;//장소
    private String locationName;//장소
    private Long hit;//조회수
    private double longitude;//경도
    private double latitude;//위도

}
