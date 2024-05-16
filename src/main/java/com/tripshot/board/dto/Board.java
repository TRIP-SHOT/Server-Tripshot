package com.tripshot.board.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Board {
	private final int id;
	private final int userId;
	private final String title;
	private final LocalDateTime createDate;
	private final String weather;
	private final String season;
	private final LocalDateTime shootDate;
	private final String image;
	private final String content;
	private final String spot;
	private final int hit;
	private final int heart_count;
	private final double longitude;//경도
	private final double latitude;//위도
}
