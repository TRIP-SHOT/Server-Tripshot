package com.tripshot.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {
    private Long id;//pk
    private Long userId;
    private String nickname; //user의 닉네임
    private String title;//제목
    private String createDate;//생성시간
    private String weather;//날씨
    private String season;//계절
    private String shotDate;//사진찍은날
    private String image;//사진
    private String content;//내용
    private String spot;//장소
    private int hit;//조회수
    private int heartCount;//좋아요수
    private String locationName;//장소명
    private double longitude;//경도
    private double latitude;//위도
    private Boolean isLike;//좋아요 여부



}
