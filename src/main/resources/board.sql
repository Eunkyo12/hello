--1. 게시판 테이블 ----------------------------------------------
DROP TABLE Board;
CREATE TABLE Board 
(
  idx 		    NUMBER 		    NOT NULL, 
  title 		  VARCHAR(100)  NOT NULL,
  content 		VARCHAR(3000) NOT NULL,
  writer		  VARCHAR(100)  NOT NULL,
  view_cnt    NUMBER        DEFAULT 0   NOT NULL,
  notice_yn   VARCHAR(1)    DEFAULT 'N' NOT NULL,
  secret_yn   VARCHAR(1)    DEFAULT 'N' NOT NULL,
  delete_yn   VARCHAR(1)    DEFAULT 'N' NOT NULL,
  insert_time DATE          DEFAULT SYSDATE NOT NULL,
  update_time DATE ,
  delete_time DATE , 
  CONSTRAINT PK_Board PRIMARY KEY(idx)
);

COMMENT ON TABLE Board IS '게시판';

COMMENT ON COLUMN Board.idx IS '번호(PK)';
COMMENT ON COLUMN Board.title IS '제목';
COMMENT ON COLUMN Board.content IS '내용';
COMMENT ON COLUMN Board.writer IS '작성자';
COMMENT ON COLUMN Board.view_cnt IS '조회 수';
COMMENT ON COLUMN Board.notice_yn IS '공지글 여부';
COMMENT ON COLUMN Board.secret_yn IS '비밀글 여부';
COMMENT ON COLUMN Board.delete_yn IS '삭제 여부';
COMMENT ON COLUMN Board.insert_time IS '등록일';
COMMENT ON COLUMN Board.update_time IS '수정일';
COMMENT ON COLUMN Board.delete_time IS '삭제일';


--2. 게시판 시퀀스 객체 ------------------------------------------
CREATE SEQUENCE seq_board_no
INCREMENT BY 1 
START WITH 1 ;

COMMIT;

SELECT * FROM board;
SELECT seq_board_no.currval FROM dual;