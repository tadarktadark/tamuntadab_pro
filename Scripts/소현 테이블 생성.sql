-- GANGEUISIL_COMM(강의실 공통 정보)
CREATE TABLE GANGEUISIL_COMM(
	GACO_ID VARCHAR2(10) PRIMARY KEY NOT NULL,
	GACO_NAME VARCHAR2(100) NOT NULL,
	GACO_SIDO VARCHAR2(10) NOT NULL,
	GACO_SIGUNGU VARCHAR2(20),
	GACO_JUSO VARCHAR2(200) NOT NULL,
	GACO_LAT NUMBER NOT NULL,
	GACO_LON NUMBER NOT NULL,
	GACO_OPEN CHAR(4),
	GACO_CLOSE CHAR(4)
);

COMMENT ON TABLE GANGEUISIL_COMM IS '강의실 공통 정보(건물 정보)를 담은 테이블';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_ID IS '건물 ID';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_NAME IS '건물 이름';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_SIDO IS '건물 시도';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_SIGUNGU IS '시군구';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_JUSO IS '건물 주소 상세';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_LAT IS '건물 위도';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_LON IS '건물 경도';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_OPEN IS '건물 오픈 시간';
COMMENT ON COLUMN GANGEUISIL_COMM.GACO_CLOSE IS '건물 마감 시간';

CREATE TABLE GANGEUISIL_GAEBYEOL(
	GAGA_ID VARCHAR2(10) PRIMARY KEY NOT NULL,
	GAGA_GACO_ID VARCHAR2(20) REFERENCES GANGEUISIL_COMM(GACO_ID) NOT NULL,
	GAGA_NAME VARCHAR2(20) NOT NULL,
	GAGA_MAX NUMBER NOT NULL,
	GAGA_HOUR_PRICE NUMBER NOT NULL,
	GAGA_YEOYU_TIME CLOB
);

COMMENT ON TABLE GANGEUISIL_GAEBYEOL IS '강의실 개별';
COMMENT ON COLUMN GANGEUISIL_GAEBYEOL.GAGA_ID IS '강의실 ID';
COMMENT ON COLUMN GANGEUISIL_GAEBYEOL.GAGA_GACO_ID IS '강의실이 있는 건물 ID';
COMMENT ON COLUMN GANGEUISIL_GAEBYEOL.GAGA_NAME IS '강의실 이름(호수)';
COMMENT ON COLUMN GANGEUISIL_GAEBYEOL.GAGA_MAX IS '최대 수용 가능한 인원';
COMMENT ON COLUMN GANGEUISIL_GAEBYEOL.GAGA_HOUR_PRICE IS '강의실 1시간 이용료';
COMMENT ON COLUMN GANGEUISIL_GAEBYEOL.GAGA_YEOYU_TIME IS '예약 가능한 날짜 및 시간';

CREATE TABLE GANGEUISIL_YEYAK(
	GAYE_ID VARCHAR2(14) PRIMARY KEY NOT NULL,
	GAYE_GAGA_ID VARCHAR2(20) NOT NULL,
	GAYE_ACCOUNT_ID VARCHAR2(14) NOT NULL,
	GAYE_PHONE_NUMBER VARCHAR2(12) NOT NULL,
	GAYE_YEYAK_DATE CHAR(8) NOT NULL,
	GAYE_START_TIME VARCHAR2(4) NOT NULL,
	GAYE_HOURS NUMBER(2) NOT NULL,
	GAYE_STATE CHAR(1) DEFAULT 'D' NOT NULL,
	GAYE_CLAS_ID NUMBER(10),
	GAYE_GYEOLJE_TYPE CHAR(1) NOT NULL,
	GAYE_GYEOLJE_USER VARCHAR2(1000) NOT NULL
);

COMMENT ON TABLE GANGEUISIL_YEYAK IS '강의실 예약';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_ID IS '강의실 예약 ID';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_GAGA_ID IS '예약된 강의실 ID';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_ACCOUNT_ID IS '예약 사용자 고유 ID';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_PHONE_NUMBER IS '예약 사용자의 연락 가능한 번호';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_YEYAK_DATE IS '예약 날짜';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_START_TIME IS '예약 시작 시간';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_HOURS IS '예약 시간';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_STATE IS '진행 상태';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_CLAS_ID IS '연관된 클래스의 ID';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_GYEOLJE_TYPE IS '결제 유형';
COMMENT ON COLUMN GANGEUISIL_YEYAK.GAYE_GYEOLJE_USER IS '결제 유저 목록';

CREATE TABLE SINGO_CATEGORY(
	SICA_ID VARCHAR2(6) PRIMARY KEY NOT NULL,
	SICA_EXPLANATION VARCHAR2(100) NOT NULL,
	SICA_DELFLAG CHAR(1) DEFAULT 'N' NOT NULL
);

COMMENT ON TABLE SINGO_CATEGORY IS '신고 카테고리에 대한 정보를 담은 테이블';
COMMENT ON COLUMN SINGO_CATEGORY.SICA_ID IS '신고 카테고리 ID';
COMMENT ON COLUMN SINGO_CATEGORY.SICA_EXPLANATION IS '신고 카테고리 설명';
COMMENT ON COLUMN SINGO_CATEGORY.SICA_DELFLAG IS '신고 카테고리 삭제 여부';

CREATE TABLE SINGO_DAESANG(
	SIDA_ID VARCHAR2(14) PRIMARY KEY NOT NULL,
	SIDA_DAESANG_ID VARCHAR2(14) NOT NULL,
	SIDA_COUNT NUMBER(1) DEFAULT 1 NOT NULL,
	SIDA_STATE CHAR(1) DEFAULT 'N' NOT NULL
);

COMMENT ON TABLE SINGO_DAESANG IS '최초로 신고된 게시글/댓글의 정보를 담은 테이블';
COMMENT ON COLUMN SINGO_DAESANG.SIDA_ID IS '신고 대상 ID';
COMMENT ON COLUMN SINGO_DAESANG.SIDA_DAESANG_ID IS '신고 대상(게시글/댓글)의 ID';
COMMENT ON COLUMN SINGO_DAESANG.SIDA_COUNT IS '신고 횟수';
COMMENT ON COLUMN SINGO_DAESANG.SIDA_STATE IS '처리 상태';

CREATE TABLE SINGO_SAYU(
	SISA_SEQ NUMBER PRIMARY KEY NOT NULL,
	SISA_ACCOUNT_ID VARCHAR2(14) NOT NULL,
	SISA_SIDA_ID VARCHAR2(14) REFERENCES SINGO_DAESANG(SIDA_ID) NOT NULL,
	SISA_CATEGORY VARCHAR2(6) NOT NULL,
	SISA_CONTENT VARCHAR2(300),
	SISA_REGDATE DATE DEFAULT CURRENT_DATE NOT NULL
);

CREATE SEQUENCE SINGO_SAYU_SEQ START WITH 121 INCREMENT BY 1 NOCACHE NOCYCLE;

COMMENT ON TABLE SINGO_SAYU IS '신고 사유에 대한 정보를 담은 테이블';
COMMENT ON COLUMN SINGO_SAYU.SISA_SEQ IS '신고 사유 SEQ';
COMMENT ON COLUMN SINGO_SAYU.SISA_ACCOUNT_ID IS '신고한 회원 고유 ID';
COMMENT ON COLUMN SINGO_SAYU.SISA_SIDA_ID IS '신고 대상 ID';
COMMENT ON COLUMN SINGO_SAYU.SISA_CATEGORY IS '신고 카테고리 ID(SICA_ID)';
COMMENT ON COLUMN SINGO_SAYU.SISA_CONTENT IS '신고 내용';
COMMENT ON COLUMN SINGO_SAYU.SISA_REGDATE IS '신고 날짜';

CREATE TABLE SINGO_USER(
	SIUS_SEQ NUMBER PRIMARY KEY NOT NULL,
	SIUS_ACCOUNT_ID VARCHAR2(14) NOT NULL,
	SIUS_COUNT NUMBER(1) DEFAULT 1 NOT NULL,
	SIUS_STATE CHAR(1) DEFAULT 'N' NOT NULL
);

CREATE SEQUENCE SINGO_USER_SEQ START WITH 7 INCREMENT BY 1 NOCACHE NOCYCLE;

COMMENT ON TABLE SINGO_USER IS '신고된 회원에 대한 정보를 담은 테이블';
COMMENT ON COLUMN SINGO_USER.SIUS_SEQ IS '신고 회원 SEQ';
COMMENT ON COLUMN SINGO_USER.SIUS_ACCOUNT_ID IS '신고된 회원 고유 ID';
COMMENT ON COLUMN SINGO_USER.SIUS_COUNT IS '신고 횟수';
COMMENT ON COLUMN SINGO_USER.SIUS_STATE IS '처리 상태';

CREATE TABLE PILGI_BOARD(
	PIBO_ID VARCHAR2(14) PRIMARY KEY NOT NULL,
	PIBO_WRITER_ID VARCHAR2(14) NOT NULL,
	PIBO_CLAS_ID NUMBER(10) NOT NULL,
	PIBO_CONTENT CLOB NOT NULL,
	PIBO_REPLY_COUNT NUMBER DEFAULT 0 NOT NULL,
	PIBO_LIKE_USER CLOB,
	PIBO_LIKE_COUNT NUMBER DEFAULT 0 NOT NULL,
	PIBO_VIEW_USER CLOB,
	PIBO_VIEW_COUNT NUMBER DEFAULT 0 NOT NULL,
	PIBO_VIEW_GROUP CHAR(1) NOT NULL,
	PIBO_DOWNLOAD_GROUP CHAR(1) NOT NULL,
	PIBO_REGDATE DATE DEFAULT CURRENT_DATE NOT NULL,
	PIBO_UPDATE DATE DEFAULT CURRENT_DATE NOT NULL,
	PIBO_STATE CHAR(1) DEFAULT 'N' NOT NULL
);

COMMENT ON TABLE PILGI_BOARD IS '필기 게시판의 게시글과 관련된 정보를 담은 테이블';
COMMENT ON COLUMN PILGI_BOARD.PIBO_ID IS '필기 게시글 ID';
COMMENT ON COLUMN PILGI_BOARD.PIBO_WRITER_ID IS '필기 게시글 작성자의 고유 ID';
COMMENT ON COLUMN PILGI_BOARD.PIBO_CLAS_ID IS '연관된 클래스 ID';
COMMENT ON COLUMN PILGI_BOARD.PIBO_CONTENT IS '필기 게시글 내용';
COMMENT ON COLUMN PILGI_BOARD.PIBO_REPLY_COUNT IS '필기 게시글 댓글수';
COMMENT ON COLUMN PILGI_BOARD.PIBO_LIKE_USER IS '해당 게시글을 좋아요한 유저 목록';
COMMENT ON COLUMN PILGI_BOARD.PIBO_LIKE_COUNT IS '해당 게시글을 좋아요한 유저수';
COMMENT ON COLUMN PILGI_BOARD.PIBO_VIEW_USER IS '해당 게시글을 조회한 유저 목록';
COMMENT ON COLUMN PILGI_BOARD.PIBO_VIEW_COUNT IS '해당 게시글을 조회한 유저수';
COMMENT ON COLUMN PILGI_BOARD.PIBO_VIEW_GROUP IS '해당 게시글의 공개 그룹';
COMMENT ON COLUMN PILGI_BOARD.PIBO_DOWNLOAD_GROUP IS '해당 게시글의 다운로드 가능 그룹';
COMMENT ON COLUMN PILGI_BOARD.PIBO_REGDATE IS '필기 게시글 게시 날짜';
COMMENT ON COLUMN PILGI_BOARD.PIBO_UPDATE IS '필기 게시글 수정 날짜';
COMMENT ON COLUMN PILGI_BOARD.PIBO_STATE IS '필기 게시글 상태';

CREATE TABLE PILGI_IMSIJEOJANG(
	PIIM_SEQ NUMBER PRIMARY KEY NOT NULL,
	PIIM_WRITER_ID VARCHAR2(20) NOT NULL,
	PIIM_CLAS_ID NUMBER(10) NOT NULL,
	PIIM_CONTENT CLOB NOT NULL,
	PIIM_VIEW_GROUP CHAR(1),
	PIIM_DOWNLOAD_GROUP CHAR(1),
	PIIM_REGDATE DATE DEFAULT CURRENT_DATE NOT NULL
);

CREATE SEQUENCE PILGI_IMSIJEOJANG_SEQ START WITH 2 INCREMENT BY 1 NOCACHE NOCYCLE;

COMMENT ON TABLE PILGI_IMSIJEOJANG IS '필기 게시판에서 임시저장된 글에 대한 정보를 담은 테이블';
COMMENT ON COLUMN PILGI_IMSIJEOJANG.PIIM_SEQ IS '필기 임시저장 SEQ';
COMMENT ON COLUMN PILGI_IMSIJEOJANG.PIIM_WRITER_ID IS '필기 게시글 작성자의 고유 ID';
COMMENT ON COLUMN PILGI_IMSIJEOJANG.PIIM_CLAS_ID IS '연관된 클래스 ID';
COMMENT ON COLUMN PILGI_IMSIJEOJANG.PIIM_CONTENT IS '필기 임시저장 내용';
COMMENT ON COLUMN PILGI_IMSIJEOJANG.PIIM_VIEW_GROUP IS '해당 게시글의 공개 그룹';
COMMENT ON COLUMN PILGI_IMSIJEOJANG.PIIM_DOWNLOAD_GROUP IS '해당 게시글의 다운로드 가능 그룹';
COMMENT ON COLUMN PILGI_IMSIJEOJANG.PIIM_REGDATE IS '게시글 임시저장 날짜';

CREATE TABLE JILMUN_BOARD(
	JIBO_ID VARCHAR2(14) PRIMARY KEY NOT NULL,
	JIBO_WRITER_ID VARCHAR2(14) NOT NULL,
	JIBO_TITLE VARCHAR2(100) NOT NULL,
	JIBO_CLAS_ID NUMBER(10),
	JIBO_SUBJECT_CODE VARCHAR2(500) NOT NULL,
	JIBO_CONTENT CLOB NOT NULL,
	JIBO_REPLY_COUNT NUMBER DEFAULT 0 NOT NULL,
	JIBO_LIKE_USER CLOB,
	JIBO_LIKE_COUNT NUMBER DEFAULT 0 NOT NULL,
	JIBO_VIEW_USER CLOB,
	JIBO_VIEW_COUNT NUMBER DEFAULT 0 NOT NULL,
	JIBO_CHAETAEK CHAR(1) DEFAULT 'N' NOT NULL,
	JIBO_REGDATE DATE DEFAULT CURRENT_DATE NOT NULL,
	JIBO_UPDATE DATE DEFAULT CURRENT_DATE NOT NULL,
	JIBO_STATE CHAR(1) DEFAULT 'N' NOT NULL
);

COMMENT ON TABLE JILMUN_BOARD IS '질문 게시판의 게시글과 관련된 정보를 담은 테이블';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_ID IS '질문 게시글 ID';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_WRITER_ID IS '질문 게시글 작성자 고유 ID';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_TITLE IS '질문 게시글 제목';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_CLAS_ID IS '연관된 클래스 ID';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_SUBJECT_CODE IS '연관된 과목 CODE 목록';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_CONTENT IS '질문 게시글 내용';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_REPLY_COUNT IS '질문 게시글 댓글수';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_LIKE_USER IS '해당 게시글을 좋아요한 유저 목록';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_LIKE_COUNT IS '해당 게시글을 좋아요한 유저수';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_VIEW_USER IS '해당 게시글을 조회한 유저 목록';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_VIEW_COUNT IS '해당 게시글을 조회한 유저수';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_CHAETAEK IS '댓글 채택 여부';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_REGDATE IS '질문 게시글 게시 날짜';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_UPDATE IS '질문 게시글 수정 날짜';
COMMENT ON COLUMN JILMUN_BOARD.JIBO_STATE IS '질문 게시글 상태';

CREATE TABLE JAYU_BOARD(
	JABO_ID VARCHAR2(14) PRIMARY KEY NOT NULL,
	JABO_WRITER_ID VARCHAR2(14) NOT NULL,
	JABO_TITLE VARCHAR2(100) NOT NULL,
	JABO_CONTENT CLOB NOT NULL,
	JABO_REPLY_COUNT NUMBER DEFAULT 0 NOT NULL,
	JABO_LIKE_USER CLOB,
	JABO_LIKE_COUNT NUMBER DEFAULT 0 NOT NULL,
	JABO_VIEW_USER CLOB,
	JABO_VIEW_COUNT NUMBER DEFAULT 0 NOT NULL,
	JABO_REGDATE DATE DEFAULT CURRENT_DATE NOT NULL,
	JABO_UPDATE DATE DEFAULT CURRENT_DATE NOT NULL,
	JABO_STATE CHAR(1) DEFAULT 'N' NOT NULL
);

COMMENT ON TABLE JAYU_BOARD IS '자유 게시판의 게시글과 관련된 정보를 담은 테이블';
COMMENT ON COLUMN JAYU_BOARD.JABO_ID IS '자유 게시글 ID';
COMMENT ON COLUMN JAYU_BOARD.JABO_WRITER_ID IS '자유 게시글 작성자 고유 ID';
COMMENT ON COLUMN JAYU_BOARD.JABO_TITLE IS '자유 게시글 제목';
COMMENT ON COLUMN JAYU_BOARD.JABO_CONTENT IS '자유 게시글 내용';
COMMENT ON COLUMN JAYU_BOARD.JABO_REPLY_COUNT IS '자유 게시글 댓글수';
COMMENT ON COLUMN JAYU_BOARD.JABO_LIKE_USER IS '해당 게시글을 좋아요한 유저 목록';
COMMENT ON COLUMN JAYU_BOARD.JABO_LIKE_COUNT IS '해당 게시글을 좋아요한 유저 목록';
COMMENT ON COLUMN JAYU_BOARD.JABO_VIEW_USER IS '해당 게시글을 조회한 유저 목록';
COMMENT ON COLUMN JAYU_BOARD.JABO_VIEW_COUNT IS '해당 게시글을 조회한 유저 목록';
COMMENT ON COLUMN JAYU_BOARD.JABO_REGDATE IS '자유 게시글 게시 날짜';
COMMENT ON COLUMN JAYU_BOARD.JABO_UPDATE IS '자유 게시글 수정 날짜';
COMMENT ON COLUMN JAYU_BOARD.JABO_STATE IS '자유 게시글 상태';

CREATE TABLE COMMUNITY_REPLY(
	CORE_SEQ NUMBER PRIMARY KEY NOT NULL,
	CORE_BOARD_ID VARCHAR2(14) NOT NULL,
	CORE_WRITER_ID VARCHAR2(14) NOT NULL,
	CORE_CONTENT CLOB NOT NULL,
	CORE_ROOT_SEQ NUMBER,
	CORE_STEP NUMBER NOT NULL,
	CORE_CHAETAEK CHAR(1) DEFAULT 'N' NOT NULL,
	CORE_REGDATE DATE DEFAULT CURRENT_DATE NOT NULL,
	CORE_UPDATE DATE DEFAULT CURRENT_DATE NOT NULL,
	CORE_STATE CHAR(1) DEFAULT 'N' NOT NULL
);

CREATE SEQUENCE COMMUNITY_REPLY_SEQ START WITH 20 INCREMENT BY 1 NOCACHE NOCYCLE;

COMMENT ON TABLE COMMUNITY_REPLY IS '각 게시판의 댓글과 관련된 정보를 담은 테이블';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_SEQ IS '댓글 SEQ';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_BOARD_ID IS '연관된 게시판 ID';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_WRITER_ID IS '댓글 작성자 고유 ID';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_CONTENT IS '댓글 내용';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_ROOT_SEQ IS 'ROOT글의 SEQ';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_STEP IS 'ROOT 그룹 내 순서';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_CHAETAEK IS '댓글 채택 여부';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_REGDATE IS '댓글 등록 날짜';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_UPDATE IS '댓글 수정 날짜';
COMMENT ON COLUMN COMMUNITY_REPLY.CORE_STATE IS '댓글 게시 상태';

CREATE TABLE SAKJE_COMMUNITY_REPLY(
	SACR_SEQ NUMBER PRIMARY KEY NOT NULL,
	SACR_BOARD_ID VARCHAR2(14) NOT NULL,
	SACR_ROOT_SEQ NUMBER,
	SACR_STEP NUMBER NOT NULL,
	SACR_REGDATE DATE DEFAULT CURRENT_DATE NOT NULL
);

COMMENT ON TABLE SAKJE_COMMUNITY_REPLY IS '삭제된 댓글에 관련된 정보를 담은 테이블';
COMMENT ON COLUMN SAKJE_COMMUNITY_REPLY.SACR_SEQ IS '삭제 SEQ';
COMMENT ON COLUMN SAKJE_COMMUNITY_REPLY.SACR_BOARD_ID IS '연관된 게시판 ID';
COMMENT ON COLUMN SAKJE_COMMUNITY_REPLY.SACR_ROOT_SEQ IS 'ROOT글의 SEQ';
COMMENT ON COLUMN SAKJE_COMMUNITY_REPLY.SACR_STEP IS 'ROOT 그룹 내 순서';
COMMENT ON COLUMN SAKJE_COMMUNITY_REPLY.SACR_REGDATE IS '댓글 등록 날짜';