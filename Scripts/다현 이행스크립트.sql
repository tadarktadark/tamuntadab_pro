CREATE TABLE TUPYO (
	TUPY_SEQ NUMBER PRIMARY KEY,
	TUPY_CLAS_ID NUMBER NOT NULL,
	TUPY_TOTAL_USER NUMBER NOT NULL,
	TUPY_STARTDATE DATE DEFAULT SYSDATE NOT NULL,
	TUPY_ENDDATE DATE NOT NULL,
	TUPY_STATUS CHAR(1) NOT NULL
);


CREATE SEQUENCE TUPY_SEQ
	START WITH 1 INCREMENT BY 1
	NOCACHE NOCYCLE;

COMMENT ON TABLE TUPYO IS '투표';
COMMENT ON COLUMN TUPYO.TUPY_SEQ IS '투표 SEQ';
COMMENT ON COLUMN TUPYO.TUPY_CLAS_ID IS '클래스 아이디';
COMMENT ON COLUMN TUPYO.TUPY_TOTAL_USER IS '투표에 참여한 전체 인원수';
COMMENT ON COLUMN TUPYO.TUPY_STARTDATE IS '투표 시작일';
COMMENT ON COLUMN TUPYO.TUPY_ENDDATE IS '투표 종료일';
COMMENT ON COLUMN TUPYO.TUPY_STATUS IS '투표 상태';


INSERT INTO TUPYO
(TUPY_SEQ, TUPY_CLAS_ID, TUPY_TOTAL_USER, TUPY_STARTDATE, TUPY_ENDDATE, TUPY_STATUS)
VALUES(TMTD.TUPY_SEQ.NEXTVAL,1000000033, 6, CURRENT_DATE , TO_DATE('2023-09-07','YYYY-MM-DD'), 'P');
INSERT INTO TUPYO
(TUPY_SEQ, TUPY_CLAS_ID, TUPY_TOTAL_USER, TUPY_STARTDATE, TUPY_ENDDATE, TUPY_STATUS)
VALUES(TMTD.TUPY_SEQ.NEXTVAL,1000000017, 3, CURRENT_DATE , TO_DATE('2023-09-07','YYYY-MM-DD'), 'P');
INSERT INTO TUPYO
(TUPY_SEQ,TUPY_CLAS_ID, TUPY_TOTAL_USER, TUPY_STARTDATE, TUPY_ENDDATE, TUPY_STATUS)
VALUES(TMTD.TUPY_SEQ.NEXTVAL,1000000018, 6, TO_DATE('2023-09-03','YYYY-MM-DD') , TO_DATE('2023-09-04','YYYY-MM-DD'), 'F');



CREATE TABLE TUPYO_OPTION(
	TUOP_SEQ NUMBER PRIMARY KEY, 
	TUOP_TUPY_SEQ NUMBER NOT NULL,
	TUOP_INSTR VARCHAR2(100) NOT NULL,
	TUOP_FEE NUMBER NOT NULL
);



CREATE SEQUENCE TUOP_SEQ
	START WITH 1 INCREMENT BY 1
	NOCACHE NOCYCLE;

COMMENT ON TABLE TUPYO_OPTION IS '투표 옵션';
COMMENT ON COLUMN TUPYO_OPTION.TUOP_SEQ IS '투표 선택지 SEQ';
COMMENT ON COLUMN TUPYO_OPTION.TUOP_TUPY_SEQ IS '투표 SEQ';
COMMENT ON COLUMN TUPYO_OPTION.TUOP_INSTR IS '투표 선택지 선생';
COMMENT ON COLUMN TUPYO_OPTION.TUOP_FEE IS '투표 선택지 수업료';



INSERT INTO TUPYO_OPTION(TUOP_SEQ, TUOP_TUPY_SEQ, TUOP_INSTR, TUOP_FEE) VALUES(TUOP_SEQ.NEXTVAL, 1, '전민균', 1000);
INSERT INTO TUPYO_OPTION(TUOP_SEQ, TUOP_TUPY_SEQ, TUOP_INSTR, TUOP_FEE) VALUES(TUOP_SEQ.NEXTVAL, 1, '소현', 2000);
INSERT INTO TUPYO_OPTION(TUOP_SEQ, TUOP_TUPY_SEQ, TUOP_INSTR, TUOP_FEE) VALUES(TUOP_SEQ.NEXTVAL, 1, '문희애', 3000);

INSERT INTO TUPYO_OPTION(TUOP_SEQ, TUOP_TUPY_SEQ, TUOP_INSTR, TUOP_FEE) VALUES(TUOP_SEQ.NEXTVAL, 2, '전민균', 1000);

INSERT INTO TUPYO_OPTION(TUOP_SEQ, TUOP_TUPY_SEQ, TUOP_INSTR, TUOP_FEE) VALUES(TUOP_SEQ.NEXTVAL, 3, '김다현', 10000);
INSERT INTO TUPYO_OPTION(TUOP_SEQ, TUOP_TUPY_SEQ, TUOP_INSTR, TUOP_FEE) VALUES(TUOP_SEQ.NEXTVAL, 3, '임정운', 4000);





CREATE TABLE TUPYO_USER(
	TUUS_SEQ NUMBER PRIMARY KEY,
	TUUS_OPTION_SEQ NUMBER NOT NULL,
	TUUS_ACCOUNT_ID VARCHAR2(320) NOT NULL,
	TUUS_AGREE CHAR(1) DEFAULT 'A'
);

CREATE SEQUENCE TUUS_SEQ
	START WITH 1 INCREMENT BY 1
	NOCACHE NOCYCLE;

COMMENT ON TABLE TUPYO_USER IS '투표 참가자	';
COMMENT ON COLUMN TUPYO_USER.TUUS_SEQ IS '투표 참가자 SEQ';
COMMENT ON COLUMN TUPYO_USER.TUUS_OPTION_SEQ IS '투표 선택지 SEQ';
COMMENT ON COLUMN TUPYO_USER.TUUS_ACCOUNT_ID IS '사용자ID';
COMMENT ON COLUMN TUPYO_USER.TUUS_AGREE IS '선택지 찬반';

INSERT INTO TUPYO_USER(TUUS_SEQ, TUUS_OPTION_SEQ, TUUS_ACCOUNT_ID, TUUS_AGREE) VALUES(TUUS_SEQ.NEXTVAL, '5', 'TMTD1','A');
INSERT INTO TUPYO_USER(TUUS_SEQ, TUUS_OPTION_SEQ, TUUS_ACCOUNT_ID, TUUS_AGREE) VALUES(TUUS_SEQ.NEXTVAL, '5', 'TMTD2','A');
INSERT INTO TUPYO_USER(TUUS_SEQ, TUUS_OPTION_SEQ, TUUS_ACCOUNT_ID, TUUS_AGREE) VALUES(TUUS_SEQ.NEXTVAL, '5', 'TMTD3','A');
INSERT INTO TUPYO_USER(TUUS_SEQ, TUUS_OPTION_SEQ, TUUS_ACCOUNT_ID, TUUS_AGREE) VALUES(TUUS_SEQ.NEXTVAL, '5', 'TMTD4','A');
INSERT INTO TUPYO_USER(TUUS_SEQ, TUUS_OPTION_SEQ, TUUS_ACCOUNT_ID, TUUS_AGREE) VALUES(TUUS_SEQ.NEXTVAL, '6', 'TMTD5','A');
INSERT INTO TUPYO_USER(TUUS_SEQ, TUUS_OPTION_SEQ, TUUS_ACCOUNT_ID, TUUS_AGREE) VALUES(TUUS_SEQ.NEXTVAL, '6', 'TMTD6','A');





CREATE TABLE GEONUI_BOARD(
	GEBO_SEQ NUMBER PRIMARY KEY,
	GEBO_TITLE VARCHAR2(100) NOT NULL,
	GEBO_ACCOUNT_ID VARCHAR2(320) NOT NULL,
	GEBO_CONTENT VARCHAR2(4000) NOT NULL,
	GEBO_CATEGORY VARCHAR2(100) NOT NULL,
	GEBO_REPLIED CHAR(1) DEFAULT 'N',
	GEBO_REGDATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE GEBO_SEQ
	START WITH 1 INCREMENT BY 1
	NOCACHE NOCYCLE;

COMMENT ON TABLE GEONUI_BOARD IS '건의';
COMMENT ON COLUMN GEONUI_BOARD.GEBO_SEQ IS '건의 SEQ';
COMMENT ON COLUMN GEONUI_BOARD.GEBO_TITLE IS '건의 제목';
COMMENT ON COLUMN GEONUI_BOARD.GEBO_ACCOUNT_ID IS '사용자ID';
COMMENT ON COLUMN GEONUI_BOARD.GEBO_CONTENT IS '건의 내용';
COMMENT ON COLUMN GEONUI_BOARD.GEBO_CATEGORY IS '건의 카테고리';
COMMENT ON COLUMN GEONUI_BOARD.GEBO_REPLIED IS '건의 답급 유무';
COMMENT ON COLUMN GEONUI_BOARD.GEBO_REGDATE IS '등록일';

INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 001', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 002', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 003', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 004', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 005', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 006', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 007', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 008', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 009', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 010', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 011', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 012', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 013', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 014', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 015', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 016', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 017', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 018', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 019', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 020', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 021', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 022', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 023', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 024', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 025', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 026', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 027', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 028', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 029', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 030', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 031', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 032', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 033', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 034', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 035', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 036', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 037', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 038', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 039', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 040', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 041', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 042', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 043', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 044', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 045', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 046', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 047', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 048', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 049', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE) VALUES(GEBO_SEQ.NEXTVAL, '건의 테스트 제목 050', 'TMTD1', '건의 테스트 내용입니다', '기타', 'N', SYSDATE);


CREATE TABLE GEONUI_REPLY(
	GERE_SEQ NUMBER PRIMARY KEY,
	GERE_GEBO_SEQ NUMBER NOT NULL,
	GERE_CONTENT VARCHAR2(4000) NOT NULL,
	GERE_REGDATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE GERE_SEQ
	START WITH 1 INCREMENT BY 1
	NOCACHE NOCYCLE;

COMMENT ON TABLE GEONUI_REPLY IS '건의 답글';
COMMENT ON COLUMN GEONUI_REPLY.GERE_SEQ IS '건의 답글 SEQ';
COMMENT ON COLUMN GEONUI_REPLY.GERE_GEBO_SEQ IS '건의 SEQ';
COMMENT ON COLUMN GEONUI_REPLY.GERE_CONTENT IS '건의 답글 내용';
COMMENT ON COLUMN GEONUI_REPLY.GERE_REGDATE IS '건의 답글 등록일';

INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 1, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 2, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 3, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 4, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 5, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 6, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 7, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 8, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 9, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 10, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 11, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 12, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 13, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 14, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 15, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 16, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 17, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 18, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 19, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 20, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 21, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 22, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 23, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 24, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 25, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 26, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 27, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 28, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 29, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 30, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 31, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 32, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 33, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 34, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 35, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 36, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 37, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 38, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 39, '건의 확인했습니다', SYSDATE);
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE) VALUES(GERE_SEQ.NEXTVAL, 40, '건의 확인했습니다', SYSDATE);




CREATE TABLE CHAT_ROOM(
	CHRO_ID VARCHAR2(50) PRIMARY KEY,
	CHRO_CLAS_ID NUMBER NOT NULL,
	CHRO_TITLE VARCHAR2(400) NOT NULL,
	CHRO_CHAT_LOG CLOB,
	CHRO_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE CHRO_SEQ
	START WITH 1 INCREMENT BY 1
	NOCACHE NOCYCLE;

COMMENT ON TABLE CHAT_ROOM IS '채팅방	';
COMMENT ON COLUMN CHAT_ROOM.CHRO_ID IS '채팅방 ID';
COMMENT ON COLUMN CHAT_ROOM.CHRO_CLAS_ID IS '클래스 ID';
COMMENT ON COLUMN CHAT_ROOM.CHRO_TITLE IS '채팅방 제목';
COMMENT ON COLUMN CHAT_ROOM.CHRO_CHAT_LOG IS '채팅 기록';
COMMENT ON COLUMN CHAT_ROOM.CHRO_DATE IS '채팅방 개설일';

INSERT INTO CHAT_ROOM
(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)
VALUES('CR230906' || TO_CHAR(CHRO_SEQ.NEXTVAL), 1000000048, 'Jenkins 구동 시연 해주세요', '', CURRENT_DATE);
INSERT INTO CHAT_ROOM
(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)
VALUES('CR230906' || TO_CHAR(CHRO_SEQ.NEXTVAL), 1000000049, '자료 구조', '', CURRENT_DATE);
INSERT INTO CHAT_ROOM
(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)
VALUES('CR230906' || TO_CHAR(CHRO_SEQ.NEXTVAL), 1000000050, '자료 구조', '', CURRENT_DATE);
INSERT INTO CHAT_ROOM
(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)
VALUES('CR230906' || TO_CHAR(CHRO_SEQ.NEXTVAL), 1000000051, '구글 애널리틱스 써 보신 분 구합니다', '', CURRENT_DATE);
INSERT INTO CHAT_ROOM
(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)
VALUES('CR230906' || TO_CHAR(CHRO_SEQ.NEXTVAL), 1000000052, 'Windows 시스템 프로그래밍 기본', '', CURRENT_DATE);
INSERT INTO CHAT_ROOM
(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)
VALUES('CR230906' || TO_CHAR(CHRO_SEQ.NEXTVAL), 1000000053, 'IT 면접 과외', '', CURRENT_DATE);
INSERT INTO CHAT_ROOM(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)VALUES('CR230906001', 1000000048, 'Jenkins 구동 시연 해주세요', '', SYSDATE);
INSERT INTO CHAT_ROOM(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)VALUES('CR230906002', 1000000049, '자료 구조', '', SYSDATE);
INSERT INTO CHAT_ROOM(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)VALUES('CR230906003', 1000000050, '자료 구조', '', SYSDATE);
INSERT INTO CHAT_ROOM(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)VALUES('CR230906004', 1000000051, '구글 애널리틱스 써 보신 분 구합니다', '', SYSDATE);
INSERT INTO CHAT_ROOM(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)VALUES('CR230906005', 1000000052, 'Windows 시스템 프로그래밍 기본', '', SYSDATE);
INSERT INTO CHAT_ROOM(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)VALUES('CR230906006', 1000000053, 'IT 면접 과외', '', SYSDATE);





CREATE TABLE CHAT_USER(
	CHUS_SEQ NUMBER PRIMARY KEY,
	CHUS_CHRO_ID VARCHAR2(50) NOT NULL,
	CHUS_ACCOUNT_ID VARCHAR2(320) NOT NULL,
	CHUS_TYPE CHAR(1)
);


CREATE SEQUENCE CHUS_SEQ
	START WITH 1 INCREMENT BY 1
	NOCACHE NOCYCLE;


COMMENT ON TABLE CHAT_USER IS '채팅방';
COMMENT ON COLUMN CHAT_USER.CHUS_SEQ IS '채팅사용자SEQ';
COMMENT ON COLUMN CHAT_USER.CHUS_CHRO_ID IS '채팅방 ID';
COMMENT ON COLUMN CHAT_USER.CHUS_ACCOUNT_ID IS '사용자ID';
COMMENT ON COLUMN CHAT_USER.CHUS_TYPE IS '사용자 구분';

INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906001', 'TMTD1', 'M');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906001', 'TMTD2', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906001', 'TMTD3', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906001', 'TMTD101', 'O');

INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906002', 'TMTD4', 'M');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906002', 'TMTD5', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906002', 'TMTD6', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906002', 'TMTD7', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906002', 'TMTD102', 'O');

INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906003', 'TMTD8', 'M');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906003', 'TMTD9', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906003', 'TMTD10', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906003', 'TMTD11', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906003', 'TMTD12', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906003', 'TMTD13', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906003', 'TMTD14', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906003', 'TMTD103', 'O');

INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906004', 'TMTD15', 'M');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906004', 'TMTD16', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906004', 'TMTD17', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906004', 'TMTD104', 'O');

INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906005', 'TMTD18', 'M');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906005', 'TMTD19', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906005', 'TMTD20', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906005', 'TMTD21', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906005', 'TMTD22', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906005', 'TMTD23', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906005', 'TMTD105', 'O');

INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906006', 'TMTD24', 'M');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906006', 'TMTD25', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906006', 'TMTD26', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906006', 'TMTD27', 'O');
INSERT INTO CHAT_USER(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE) VALUES(CHUS_SEQ.NEXTVAL, 'CR230906006', 'TMTD106', 'O');




CREATE TABLE GONGJI_BOARD(
	GOBO_ID        VARCHAR2(50) PRIMARY KEY,
	GOBO_TITLE     VARCHAR2(400) NOT NULL,
	GOBO_CONTENT   VARCHAR2(4000) NOT NULL,
	GOBO_CATEGORY  VARCHAR2(100) NOT NULL,
	GOBO_REGDATE   DATE DEFAULT SYSDATE NOT NULL
);

COMMENT ON TABLE GONGJI_BOARD IS '공지';
COMMENT ON COLUMN GONGJI_BOARD.GOBO_ID IS '공지 ID';
COMMENT ON COLUMN GONGJI_BOARD.GOBO_TITLE IS '공지 제목';
COMMENT ON COLUMN GONGJI_BOARD.GOBO_CONTENT IS '공지 내용';
COMMENT ON COLUMN GONGJI_BOARD.GOBO_CATEGORY IS '공지 카테고리';
COMMENT ON COLUMN GONGJI_BOARD.GOBO_REGDATE IS '공지 작성일';


INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100001', '공지 테스트 제목001', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100002', '공지 테스트 제목002', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100003', '공지 테스트 제목003', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100004', '공지 테스트 제목004', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100005', '공지 테스트 제목005', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100006', '공지 테스트 제목006', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100007', '공지 테스트 제목007', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100008', '공지 테스트 제목008', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100009', '공지 테스트 제목009', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100010', '공지 테스트 제목010', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100011', '공지 테스트 제목011', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100012', '공지 테스트 제목012', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100013', '공지 테스트 제목013', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100014', '공지 테스트 제목014', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100015', '공지 테스트 제목015', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100016', '공지 테스트 제목016', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100017', '공지 테스트 제목017', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100018', '공지 테스트 제목018', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100019', '공지 테스트 제목019', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100020', '공지 테스트 제목020', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100021', '공지 테스트 제목021', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100022', '공지 테스트 제목022', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100023', '공지 테스트 제목023', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100024', '공지 테스트 제목024', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100025', '공지 테스트 제목025', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100026', '공지 테스트 제목026', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100027', '공지 테스트 제목027', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100028', '공지 테스트 제목028', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100029', '공지 테스트 제목029', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100030', '공지 테스트 제목030', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100031', '공지 테스트 제목031', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100032', '공지 테스트 제목032', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100033', '공지 테스트 제목033', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100034', '공지 테스트 제목034', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100035', '공지 테스트 제목035', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100036', '공지 테스트 제목036', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100037', '공지 테스트 제목037', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100038', '공지 테스트 제목038', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100039', '공지 테스트 제목039', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100040', '공지 테스트 제목040', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100041', '공지 테스트 제목041', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100042', '공지 테스트 제목042', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100043', '공지 테스트 제목043', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100044', '공지 테스트 제목044', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100045', '공지 테스트 제목045', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100046', '공지 테스트 제목046', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100047', '공지 테스트 제목047', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100048', '공지 테스트 제목048', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100049', '공지 테스트 제목049', '공지 테스트 내용입니다', '업데이트', SYSDATE );
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE) VALUES('GJ100050', '공지 테스트 제목050', '공지 테스트 내용입니다', '업데이트', SYSDATE );

CREATE TABLE ALARM(
	ALAR_ID           VARCHAR2(100) NOT NULL,
	ALAR_ACCOUNT_ID   VARCHAR2(320) NOT NULL,
	ALAR_CONTENT      VARCHAR2(1000),
	ALAR_EVENTDATE    DATE DEFAULT SYSDATE,
	ALAR_REPLY_SEQ VARCHAR2(100),
	ALAR_CHECKED      CHAR(1) DEFAULT 'N'
);


COMMENT ON TABLE ALARM IS '알림';
COMMENT ON COLUMN ALARM.ALAR_ID IS '알림 ID';
COMMENT ON COLUMN ALARM.ALAR_ACCOUNT_ID IS '사용자 ID';
COMMENT ON COLUMN ALARM.ALAR_CONTENT IS '알림 내용';
COMMENT ON COLUMN ALARM.ALAR_EVENTDATE IS '알림 발생 시간';
COMMENT ON COLUMN ALARM.ALAR_REPLY_SEQ IS '답글 SEQ';
COMMENT ON COLUMN ALARM.ALAR_CHECKED IS '확인 여부';

INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906001', 'TMTD1', '알림 테스트 1 답글로 이동', SYSDATE, '1', 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906002', 'TMTD1', '알림 테스트 2', SYSDATE, NULL, 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906003', 'TMTD1', '알림 테스트 3', SYSDATE, NULL, 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906004', 'TMTD1', '알림 테스트 4', SYSDATE, NULL, 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906005', 'TMTD1', '알림 테스트 5', SYSDATE, NULL, 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906006', 'TMTD1', '알림 테스트 6', SYSDATE, NULL, 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906007', 'TMTD1', '알림 테스트 7', SYSDATE, NULL, 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906008', 'TMTD1', '알림 테스트 8', SYSDATE, NULL, 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906009', 'TMTD1', '알림 테스트 9', SYSDATE, NULL, 'N');
INSERT INTO ALARM(ALAR_ID, ALAR_ACCOUNT_ID, ALAR_CONTENT, ALAR_EVENTDATE, ALAR_REPLY_SEQ, ALAR_CHECKED) VALUES('AL230906010', 'TMTD1', '알림 테스트 10', SYSDATE, NULL, 'N');



CREATE TABLE "FILE"(
FILE_REF_PK VARCHAR2(50) NOT NULL,
FILE_ORIGIN_NAME VARCHAR2(1000) NOT NULL,
FILE_SAVE_NAME VARCHAR2(1000) NOT NULL,
FILE_REGDATE DATE DEFAULT SYSDATE
);

COMMENT ON TABLE "FILE" IS '파일';
COMMENT ON COLUMN "FILE".FILE_REF_PK IS '참조테이블의 PK';
COMMENT ON COLUMN "FILE".FILE_ORIGIN_NAME IS '원본파일명';
COMMENT ON COLUMN "FILE".FILE_SAVE_NAME IS '저장된 파일명(UUID)';
COMMENT ON COLUMN "FILE".FILE_REGDATE IS '파일 업로드일';











