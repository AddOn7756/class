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
INSERT INTO USERS VALUES (1, '��浿', 'kim', '1234', '01012341234', 'M', 'xxssgg120@naver.com', '��⵵ ������', '19960205', '1');
INSERT INTO USERS VALUES (2, 'ȫ�浿', 'hong', '1234', '01012345678', 'F', 'asdf1234@naver.com', '��⵵ �Ⱦ��', '19991021', '2');
/* ����  */
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

INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�1','�Խñ� ����1','kim','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'�����Խ���','�Խñ�2','�Խñ� ����2','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�3','�Խñ� ����3','kim','C#');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'�����Խ���','�Խñ�4','�Խñ� ����4','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�5','�Խñ� ����5','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'�����Խ���','�Խñ�6','�Խñ� ����6','hong','C#');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'�����Խ���','�Խñ�7','�Խñ� ����7','hong','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�8','�Խñ� ����8','kim','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�9','�Խñ� ����9','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'�����Խ���','�Խñ�10','�Խñ� ����10','hong','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�11','�Խñ� ����11','kim','C#');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'�����Խ���','�Խñ�12','�Խñ� ����12','hong','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�13','�Խñ� ����13','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�14','�Խñ� ����14','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�15','�Խñ� ����15','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�16','�Խñ� ����16','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�17','�Խñ� ����17','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�18','�Խñ� ����18','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�19','�Խñ� ����19','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�20','�Խñ� ����20','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�21','�Խñ� ����21','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�22','�Խñ� ����22','kim','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'�����Խ���','�Խñ�23','�Խñ� ����23','kim','C');

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

INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'���1','hong',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'���2','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'����1','hong',1);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'����2','kim',1);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'���3','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'���5','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'���6','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'���7','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'���8','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'���9','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'���10','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'���11','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),2,1,'���12','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'����6','kim',1);

/*===========================================================================================*/

SELECT * FROM (
SELECT ROWNUM AS RNUM, BOARD.* FROM (
SELECT * FROM BOARD WHERE B_CTGR = '�����Խ���' AND B_TITLE LIKE '%' ORDER BY B_HIT DESC, B_DATE DESC
) BOARD WHERE ROWNUM <= 50
) WHERE RNUM > 0 ORDER BY B_HIT DESC;


