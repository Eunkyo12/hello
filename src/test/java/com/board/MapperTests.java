package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class MapperTests {

  @Autowired
  private BoardMapper boardMapper;

  @Test
  public void testOfInsert() {
    BoardDTO params = new BoardDTO();
    params.setTitle("테스트 게시글 제목");
    params.setContent("테스트 게시글 내용");
    params.setWriter("테스터");

    int result = boardMapper.insertBoard(params);
    System.out.println("결과는 " + result + "입니다.");
  }

  @Test
  public void testMultipleInsert() {
    for (int i = 1; i <= 50; i++) {
      BoardDTO params = new BoardDTO();
      params.setTitle(i + "번 게시글 제목");
      params.setContent(i + "번 게시글 내용");
      params.setWriter(i + "번 게시글 작성자");

      // params.setNoticeYn("N");
      /*
       * Mybatis configuration 중에 jdbcTypeForNull 이라는 설정 하지 않은경우 Mapper 해당컬럼에
       * NVL(#{noticeYn, jdbcType=VARCHAR}, 'N') 로 써야함...
       */
      boardMapper.insertBoard(params);
    }
  }

  @Test
  public void testOfSelectDetail() {
    BoardDTO board = boardMapper.selectBoardDetail((long) 163);
    try {
      // String boardJson = new ObjectMapper().writeValueAsString(board);
      String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

      System.out.println("=========================");
      System.out.println(boardJson);
      System.out.println("=========================");

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testOfUpdate() {
    BoardDTO params = new BoardDTO();
    params.setTitle("1번 게시글 제목을 수정합니다.");
    params.setContent("1번 게시글 내용을 수정합니다.");
    params.setWriter("홍길동");
    params.setIdx((long) 265);

    int result = boardMapper.updateBoard(params);
    if (result == 1) {
      BoardDTO board = boardMapper.selectBoardDetail((long) 265);
      try {
        // String boardJson = new ObjectMapper().writeValueAsString(board);
        String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

        System.out.println("=========================");
        System.out.println(boardJson);
        System.out.println("=========================");

      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testOfDelete() {
    int result = boardMapper.deleteBoard((long) 265);
    if (result == 1) {
      BoardDTO board = boardMapper.selectBoardDetail((long) 265);
      try {
        // String boardJson = new ObjectMapper().writeValueAsString(board);
        String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

        System.out.println("=========================");
        System.out.println(boardJson);
        System.out.println("=========================");

      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
  }

//	@Test
//	public void testSelectList() {
//		int boardTotalCount = boardMapper.selectBoardTotalCount();
//		if (boardTotalCount > 0) {
//			List<BoardDTO> boardList = boardMapper.selectBoardList();
//			if (CollectionUtils.isEmpty(boardList) == false) {
//				for (BoardDTO board : boardList) {
//					System.out.println("=========================");
//					System.out.println(board.getTitle());
//					System.out.println(board.getContent());
//					System.out.println(board.getWriter());
//					System.out.println("=========================");
//				}
//			}
//		}
//	}

//  @Test
//  public void testSelectList() {
//    Criteria criteria = new Criteria();
//    criteria.setCurrentPageNo(2);
//    criteria.setRecordsPerPage(10);
//    criteria.setPageSize(10);
//
//    System.out.println("=========================");
//    System.out.println("CurrentPageNo=" + criteria.getCurrentPageNo());
//    System.out.println("RecordsPerPage=" + criteria.getRecordsPerPage());
//    System.out.println("PageSize=" + criteria.getPageSize());
//
//    int boardTotalCount = boardMapper.selectBoardTotalCount(criteria);
//
//    System.out.println("boardTotalCount=" + boardTotalCount);
//    System.out.println("=========================");
//
//    if (boardTotalCount > 0) {
//      List<BoardDTO> boardList = boardMapper.selectBoardList(criteria);
//      if (CollectionUtils.isEmpty(boardList) == false) {
//        for (BoardDTO board : boardList) {
//          System.out.println("=========================");
//          System.out.println(board.getTitle());
//          System.out.println(board.getContent());
//          System.out.println(board.getWriter());
//          System.out.println("=========================");
//        }
//      }
//    }
//  }
  
  @Test
  public void testSelectList() {
    Criteria criteria = new Criteria();
    criteria.setCurrentPageNo(3);
    criteria.setRecordsPerPage(10);
    criteria.setPageSize(10);
    
    PaginationInfo paginationInfo = new PaginationInfo(criteria);
    
    BoardDTO boardDTO = new BoardDTO();
    boardDTO.setPaginationInfo(paginationInfo);

    System.out.println("=========================");
    System.out.println("CurrentPageNo=" + boardDTO.getCurrentPageNo());
    System.out.println("RecordsPerPage=" + boardDTO.getRecordsPerPage());
    System.out.println("PageSize=" + boardDTO.getPageSize());

    int boardTotalCount = boardMapper.selectBoardTotalCount(boardDTO);

    System.out.println("boardTotalCount=" + boardTotalCount);
    System.out.println("=========================");
    
    paginationInfo.setTotalRecordCount(boardTotalCount);
    boardDTO.setPaginationInfo(paginationInfo);
    
    if (boardTotalCount > 0) {
      List<BoardDTO> boardList = boardMapper.selectBoardList(boardDTO);
      if (CollectionUtils.isEmpty(boardList) == false) {
        for (BoardDTO board : boardList) {
          System.out.println("=========================");
          System.out.println(board.getTitle());
          System.out.println(board.getContent());
          System.out.println(board.getWriter());
          System.out.println("=========================");
        }
      }
    }
  }

}
