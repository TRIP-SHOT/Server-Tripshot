package com.tripshot.map.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttractionInfoDto {
	private int content_id;
	private String title;
	private String addr1;
	private String first_image;
	private BigDecimal latitude;
	private BigDecimal longitude;
}
