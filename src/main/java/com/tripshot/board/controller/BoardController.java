package com.tripshot.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripshot.board.dto.Board;
import com.tripshot.board.service.BoardService;
import com.tripshot.global.ApiResponse;

@CrossOrigin("*")
@RequestMapping("/boards")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    /**
     * 조회 및 검색
     * @param season 계절(봄,여름,가을,겨울)
     * @param startDate 필터시작일
     * @param endDate 종료일
     * @param keyword 검색어
     * @return 게시글 목록
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Board>>> list(
            @RequestParam(value = "season", required = false) String season,
            @RequestParam(value = "startdate", required = false)  String startDate,
            @RequestParam(value = "enddate", required = false) String endDate,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        List<Board> response = null;
        if (season != null || startDate != null || endDate != null || keyword != null) {//검색 조건이 있는 경우
            response = service.search(season, startDate, endDate, keyword);
        } else { //검색 기준이 없는 경우
            response = service.selectAll();
        }
        return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 목록 조회 성공", response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Board>> detail(@PathVariable("id") Long id){
        System.out.println("in getMapping + id=" + id);
        Board response = service.selectOne(id);

        return new ResponseEntity(new ApiResponse(HttpStatus.OK, "게시글 상세조회 조회 성공", response), HttpStatus.OK);
    }
}
