package com.tripshot.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Schema(description = "DTO for creating a new board post")
public class WriteBoardRequestDto {
    @Schema(description = "Board ID", example = "1")
    private Long id;

    @Schema(description = "User ID", example = "1")
    private Long userId;

    @Schema(description = "Title of the board post", example = "My Trip to the Mountains")
    private String title;

    @Schema(description = "Creation date of the post", example = "2023-05-21")
    private String createDate;

    @Schema(description = "Weather during the trip", example = "Sunny")
    private String weather;

    @Schema(description = "Season of the trip", example = "Spring")
    private String season;

    @Schema(description = "Date when the photo was taken", example = "2023-05-20")
    private String shotDate;

    @Schema(description = "Content of the board post", example = "It was a wonderful trip...")
    private String content;

    @Schema(description = "Spot of the trip", example = "Mountain Peak")
    private String spot;

    @Schema(description = "Name of the location", example = "Mount Everest")
    private String locationName;

    @Schema(description = "Number of hits", example = "100")
    private Long hit;

    @Schema(description = "Longitude of the location", example = "86.9250")
    private double longitude;

    @Schema(description = "Latitude of the location", example = "27.9881")
    private double latitude;

    @Schema(description = "Image file of the board post")
    private MultipartFile image;

    public Board toBoard() {
        return Board.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .createDate(createDate)
                .weather(weather)
                .season(season)
                .shotDate(shotDate)
                .content(content)
                .spot(spot)
                .locationName(locationName)
                .hit(hit)
                .longitude(longitude)
                .latitude(latitude)
                .build();
    }
}
