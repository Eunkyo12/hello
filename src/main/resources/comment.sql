--3. 댓글 테이블 ----------------------------------------------
CREATE TABLE tb_comment (
	idx 		    NUMBER 		    NOT NULL,
	board_idx   NUMBER        NOT NULL,
	content 	  VARCHAR(3000) NOT NULL,
	writer		  VARCHAR(100)  NOT NULL,
	delete_yn   VARCHAR(1)    DEFAULT 'N'     NOT NULL,
	insert_time DATE          DEFAULT SYSDATE NOT NULL,
	update_time DATE ,
	delete_time DATE , 
  CONSTRAINT PK_tb_comment PRIMARY KEY(idx)
);

COMMENT ON TABLE tb_comment IS '댓글';

COMMENT ON COLUMN tb_comment.idx IS '번호(PK)';
COMMENT ON COLUMN tb_comment.board_idx IS '게시글 번호 (FK)';
COMMENT ON COLUMN tb_comment.content IS '내용';
COMMENT ON COLUMN tb_comment.writer IS '작성자';
COMMENT ON COLUMN tb_comment.delete_yn IS '삭제 여부';
COMMENT ON COLUMN tb_comment.insert_time IS '등록일';
COMMENT ON COLUMN tb_comment.update_time IS '수정일';
COMMENT ON COLUMN tb_comment.delete_time IS '삭제일';
    
--4. 댓글 시퀀스 객체 ------------------------------------------
DROP SEQUENCE seq_board_no;
CREATE SEQUENCE seq_tb_comment_idx
INCREMENT BY 1 
START WITH 1 ;


SELECT * FROM tb_comment;

COMMIT;