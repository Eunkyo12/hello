package com.board.domain;

import lombok.Data;

@Data
public class BoardDTO extends CommonDTO {

  private Long idx; // 번호 (PK)
  private String title; // 제목
  private String content; // 내용
  private String writer; // 작성자
  private int viewCnt; // 조회 수
  private String noticeYn; // 공지 여부
  private String secretYn; // 비밀 여부

}