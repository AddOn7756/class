drop table users
drop table board
drop table test
drop table board_reply
drop table test_reply

create table users(
   user_num int primary key,
   user_name varchar(15) not null,
   user_id varchar(50) not null,
   user_pw varchar(50) not null,
   user_hp varchar(25) not null,
   user_gender varchar(5) not null,
   user_email varchar(255) not null,
   user_addr varchar(255) not null,
   user_birth varchar(30) not null,
   icon_id varchar(30) not null

)

SELECT * FROM USERS;
INSERT INTO USERS VALUES (1, '김길동', 'kim', '1234', '01012341234', 'M', 'xxssgg120@naver.com', '경기도 군포시', '19960205', '1');
INSERT INTO USERS VALUES (2, '홍길동', 'hong', '1234', '01012345678', 'F', 'asdf1234@naver.com', '경기도 안양시', '19991021', '2');
/* 보드  */
create table board(
   b_id int primary key,
   user_num int not null,
   b_ctgr varchar(50) not null,
   b_title varchar(100) not null,
   b_content varchar(4000) not null,
   b_writer varchar(50) not null,
   b_date date default sysdate,
   b_hit int default 0,
   b_lang varchar(20) not null,
   re_cnt int default 0,
   constraint board_cons foreign key (user_num) references users(user_num) on delete cascade
);
select * from board;

INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글1','게시글 내용1','kim','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'자유게시판','게시글2','게시글 내용2','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글3','게시글 내용3','kim','C#');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'자유게시판','게시글4','게시글 내용4','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글5','게시글 내용5','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'자유게시판','게시글6','게시글 내용6','hong','C#');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'자유게시판','게시글7','게시글 내용7','hong','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글8','게시글 내용8','kim','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글9','게시글 내용9','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'자유게시판','게시글10','게시글 내용10','hong','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글11','게시글 내용11','kim','C#');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'자유게시판','게시글12','게시글 내용12','hong','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글13','게시글 내용13','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글14','게시글 내용14','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글15','게시글 내용15','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글16','게시글 내용16','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글17','게시글 내용17','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글18','게시글 내용18','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글19','게시글 내용19','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글20','게시글 내용20','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글21','게시글 내용21','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글22','게시글 내용22','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'자유게시판','게시글23','게시글 내용23','kim','C');

create table board_reply(

   r_id int primary key,
   b_id int not null,
   user_num int not null,
   r_content varchar(255) not null,
   r_date date default sysdate,
   delete_at varchar(1) default 'N',
   r_writer varchar(20) not null,
   parent_id int not null,
   constraint b_id_cons foreign key (b_id) references board(b_id) on delete cascade,
   constraint user_num_cons2 foreign key (user_num) references users(user_num) on delete cascade
);

INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글1','hong',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'댓글2','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'대댓글1','hong',1);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'대댓글2','kim',1);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'댓글3','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'댓글5','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'댓글6','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'댓글7','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'댓글8','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'댓글9','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'댓글10','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'댓글11','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'댓글12','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'대댓글6','kim',1);

/*===========================================================================================*/

SELECT * FROM (
SELECT ROWNUM AS RNUM, BOARD.* FROM (
SELECT * FROM BOARD WHERE B_CTGR = '자유게시판' AND B_TITLE LIKE '%' ORDER BY B_HIT DESC, B_DATE DESC
) BOARD WHERE ROWNUM <= 50
) WHERE RNUM > 0 ORDER BY B_HIT DESC;


