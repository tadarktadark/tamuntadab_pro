-----------------------------투표 시작----------------------------

--유저 조회
SELECT USER_ACCOUNT_ID ,USER_EMAIL ,USER_NAME
	FROM USER_PROFILE up 
	WHERE USER_ACCOUNT_ID ='TMTD152';

--투표 생성
INSERT INTO TUPYO(TUPY_SEQ, TUPY_CLAS_ID, TUPY_TOTAL_USER, TUPY_STARTDATE, TUPY_ENDDATE, TUPY_STATUS)
	VALUES(TUPY_SEQ.NEXTVAL,1000000999, 6, CURRENT_DATE , TO_DATE('2023-09-20','YYYY-MM-DD'), 'P');

--클래스 참여 중인 강사 조회
SELECT CLCH_ACCOUNT_ID ,CLCH_CLAS_ID ,CLCH_STATUS ,CLCH_YEOKAL 
	FROM CLASS_CHAMYEOJA cc 
	WHERE CLCH_CLAS_ID ='1000000293' AND CLCH_YEOKAL ='I';


--클래스 개설자 조회
SELECT CLCH_ACCOUNT_ID ,CLCH_YEOKAL 
	FROM CLASS_CHAMYEOJA
	WHERE CLCH_CLAS_ID ='1000000293' AND CLCH_YEOKAL ='M' AND  CLCH_STATUS ='Y';

--클래스에 참여한 학생 조회
SELECT CLCH_ID,CLCH_ACCOUNT_ID ,CLCH_CLAS_ID ,CLCH_STATUS ,CLCH_GYEOLJE_STATUS ,CLCH_YEOKAL
	FROM CLASS_CHAMYEOJA cc
	WHERE CLCH_CLAS_ID ='1000000293';

--클래스에 참여한 학생인지 판단
SELECT CLCH_ACCOUNT_ID ,CLCH_YEOKAL
	FROM CLASS_CHAMYEOJA cc
	WHERE CLCH_CLAS_ID ='1000000033' AND CLCH_ACCOUNT_ID ='TMTD3' AND CLCH_STATUS ='Y' AND CLCH_YEOKAL IN ('M','S');

--참여중인 학생 수 계산
SELECT COUNT(*)  
	FROM CLASS_CHAMYEOJA cc
	WHERE CLCH_CLAS_ID ='1000000033' AND CLCH_YEOKAL IN ('M','S') AND  CLCH_STATUS ='Y';


--투표 조회
SELECT TUPY_SEQ ,TUPY_CLAS_ID ,TUPY_TOTAL_USER ,TO_CHAR(TUPY_STARTDATE,'YYYY-MM-DD') AS TUPY_STARTDATE  ,TO_CHAR(TUPY_ENDDATE,'YYYY-MM-DD') AS TUPY_ENDDATE ,TUPY_STATUS
	FROM TUPYO t
	WHERE TUPY_CLAS_ID ='1000000033';



--투표 선택지 조회
SELECT TUOP_SEQ ,TUOP_TUPY_SEQ ,TUOP_INSTR ,TUOP_FEE 
	FROM TUPYO_OPTION to2 
	WHERE TUOP_TUPY_SEQ =1;

UPDATE CLASS 
	SET CLAS_ACCOUNT_ID = 'TMTD101'
	WHERE CLAS_ID = 1;
	
--특정 선택지 조회
SELECT TUOP_SEQ ,TUOP_TUPY_SEQ ,TUOP_INSTR ,TUOP_FEE 
	FROM TUPYO_OPTION to2 
	WHERE TUOP_SEQ =1;
             
--선택지 생성
INSERT INTO TUPYO_OPTION(TUOP_SEQ, TUOP_TUPY_SEQ, TUOP_INSTR, TUOP_FEE) VALUES(TUOP_SEQ.NEXTVAL, 11, '전민균', 1000);


--투표 삭제
DELETE FROM TUPYO t 
WHERE TUPY_SEQ =4;

-- 투표옵션 삭제
DELETE FROM TUPYO_OPTION 
WHERE TUOP_TUPY_SEQ = 4;


-- 투표유저 삭제
DELETE FROM TUPYO_USER 
WHERE TUUS_OPTION_SEQ IN (
    SELECT TUOP_SEQ 
    FROM TUPYO_OPTION 
    WHERE TUOP_TUPY_SEQ = 4
);




--투표 유무 판단
SELECT TUUS_SEQ 
    FROM TUPYO_USER 
    WHERE TUUS_ACCOUNT_ID = 'TMTD1' AND 
          TUUS_OPTION_SEQ IN (
              SELECT TUOP_SEQ 
              FROM TUPYO_OPTION 
              WHERE TUOP_TUPY_SEQ = 1);
             
             
--투표 진행
INSERT INTO TUPYO_USER(TUUS_SEQ, TUUS_OPTION_SEQ, TUUS_ACCOUNT_ID, TUUS_AGREE)
	VALUES(TUUS_SEQ.NEXTVAL, 3, 'TMTD4', 'A');


--투표 결과 조회				
SELECT ot.TUOP_SEQ, NVL(tu.count, 0) AS COUNT
FROM TUPYO_OPTION ot 
LEFT JOIN (
    SELECT TUUS_OPTION_SEQ, COUNT(*) as count
        FROM TUPYO_USER 
        WHERE TUUS_OPTION_SEQ IN (
                                                        SELECT TUOP_SEQ 
                                                        FROM TUPYO_OPTION to2 
                                                        WHERE TUOP_TUPY_SEQ =(
                                                                                                SELECT TUOP_TUPY_SEQ 
                                                                                                        FROM TUPYO_OPTION to2 
                                                                                                        WHERE TUOP_SEQ =14
                                                                                                )
                                                )
        GROUP BY TUUS_OPTION_SEQ) tu ON ot.TUOP_SEQ = tu.TUUS_OPTION_SEQ
WHERE ot.TUOP_TUPY_SEQ =(SELECT TUOP_TUPY_SEQ FROM TUPYO_OPTION WHERE TUOP_SEQ =14)
ORDER BY TUOP_SEQ ;







--찬반 투표 결과 전체 조회
SELECT TUUS_ACCOUNT_ID ,TUUS_AGREE
	FROM TUPYO_USER tu 
	WHERE TUUS_OPTION_SEQ  IN (
					SELECT TUOP_SEQ 
						FROM TUPYO_OPTION to2 
						WHERE TUOP_TUPY_SEQ =4);

 AND ;
				

--이게 0이라면
SELECT COUNT(*) 
	FROM TUPYO_USER tu 
	WHERE TUUS_OPTION_SEQ IN(
							SELECT TUOP_SEQ 
								FROM TUPYO_OPTION to2 
								WHERE TUOP_TUPY_SEQ = '17'
							);


--재투표(투표 취소)
DELETE FROM TUPYO_USER tu 
	WHERE TUUS_ACCOUNT_ID ='TMTD1' AND TUUS_OPTION_SEQ =1;


--투표 종료
UPDATE TUPYO
SET TUPY_STATUS='F'
WHERE TUPY_SEQ=13;


--투표 인원수 변경
UPDATE TUPYO
SET TUPY_TOTAL_USER = TUPY_TOTAL_USER - 1
WHERE TUPY_SEQ=13;


--찬반 투표 구분
SELECT TUUS_SEQ, TUUS_OPTION_SEQ, TUUS_ACCOUNT_ID, TUUS_AGREE 
	FROM TUPYO_USER tu 
	WHERE TUUS_OPTION_SEQ = 6 AND TUUS_AGREE = 'A'
	ORDER BY TUUS_SEQ DESC;

--찬반 투표 개수
SELECT COUNT(*)
	FROM TUPYO_USER tu 
	WHERE TUUS_OPTION_SEQ =15 AND TUUS_AGREE ='A';


--찬반 투표 반영
UPDATE TUPYO_USER 
	SET TUUS_AGREE = 'D'
	WHERE TUUS_SEQ =8;

--클래스 강사 확정 상태변경
UPDATE CLASS 
	SET CLAS_STATUS ='매칭완료'
	WHERE CLAS_ID ='1000000111';

--투표 종료 CRON
UPDATE TUPYO 
SET TUPY_STATUS = 'P'
WHERE TRUNC(TUPY_ENDDATE) <= TRUNC(SYSDATE);



-----------------------------투표 끝----------------------------

----------------------------공지 시작---------------------------

--공지 목록 조회
SELECT GOBO_ID ,GOBO_TITLE  ,GOBO_CATEGORY ,GOBO_REGDATE  FROM (
	  SELECT gb.GOBO_ID, gb.GOBO_TITLE, gb.GOBO_CATEGORY, gb.GOBO_REGDATE,
	         ROW_NUMBER() OVER (ORDER BY gb.GOBO_REGDATE DESC) as rn
	  FROM GONGJI_BOARD gb
	)
	WHERE rn BETWEEN 1 AND 10
	ORDER BY GOBO_REGDATE DESC;

--공지 전체 개수
SELECT COUNT(*)
	FROM GONGJI_BOARD gb ;

--카테고리별 공지 목록 조회
SELECT GOBO_ID ,GOBO_TITLE ,GOBO_CATEGORY ,GOBO_REGDATE
	FROM GONGJI_BOARD gb 
	WHERE GOBO_CATEGORY ='업데이트'
	ORDER BY GOBO_REGDATE DESC;
--공지 상세 조회
SELECT GOBO_ID ,GOBO_TITLE, GOBO_CONTENT ,GOBO_CATEGORY ,GOBO_REGDATE
	FROM GONGJI_BOARD gb 
	WHERE GOBO_ID ='GJ100001';
--공지 등록
INSERT INTO GONGJI_BOARD(GOBO_ID, GOBO_TITLE, GOBO_CONTENT, GOBO_CATEGORY, GOBO_REGDATE)
	VALUES('GJ100051', '공지 입력 테스트', '공지 입력 테스트 중 입니다', '안내', CURRENT_DATE );
--공지 수정
UPDATE GONGJI_BOARD SET GOBO_TITLE='수정', GOBO_CONTENT='수정', GOBO_CATEGORY='수정'
	WHERE GOBO_ID='GJ100001';
--공지 삭제
DELETE FROM GONGJI_BOARD
	WHERE GOBO_ID='GJ100001';



----------------------------공지 끝---------------------------

----------------------------건의사항 시작---------------------------

--건의 조회
SELECT GEBO_SEQ ,GEBO_TITLE ,GEBO_ACCOUNT_ID ,GEBO_CATEGORY , GEBO_REPLIED ,GEBO_REGDATE
	FROM GEONUI_BOARD gb 
	WHERE GEBO_ACCOUNT_ID ='TMTD1'
	ORDER BY GEBO_REGDATE DESC;
--건의 상세 조회
SELECT GEBO_SEQ ,GEBO_TITLE ,GEBO_ACCOUNT_ID ,GEBO_CATEGORY , GEBO_REPLIED ,GEBO_REGDATE
	FROM GEONUI_BOARD gb 
	WHERE GEBO_SEQ ='1';
--건의 답글 조회
SELECT GERE_SEQ ,GERE_GEBO_SEQ ,GERE_CONTENT ,GERE_REGDATE 
	FROM GEONUI_REPLY gr
	WHERE GERE_GEBO_SEQ ='1';
--건의 등록
INSERT INTO GEONUI_BOARD(GEBO_SEQ, GEBO_TITLE, GEBO_ACCOUNT_ID, GEBO_CONTENT, GEBO_CATEGORY, GEBO_REPLIED, GEBO_REGDATE)
VALUES(GEBO_SEQ.NEXTVAL, '건의 입력 테스트', 'TMTD1', '건의 입력 테스트입니다', '기타', 'N', CURRENT_DATE);
--건의 삭제
DELETE FROM GEONUI_BOARD
	WHERE GEBO_SEQ=52;
--운영자 건의 조회
SELECT GEBO_SEQ ,GEBO_TITLE ,GEBO_ACCOUNT_ID ,GEBO_CATEGORY , GEBO_REPLIED ,GEBO_REGDATE, USER_NICKNAME, USER_EMAIL
	FROM GEONUI_BOARD gb JOIN USER_PROFILE up ON gb.GEBO_ACCOUNT_ID = up.USER_ACCOUNT_ID WHERE GEBO_REPLIED = 'N'
	ORDER BY GEBO_REGDATE DESC;
--건의 답글 등록
INSERT INTO GEONUI_REPLY(GERE_SEQ, GERE_GEBO_SEQ, GERE_CONTENT, GERE_REGDATE)
	VALUES(GERE_SEQ.NEXTVAL, 41, '건의 답글 테스트 달았습니다', CURRENT_DATE);
--건의 답글 수정
UPDATE GEONUI_REPLY SET GERE_CONTENT='건의 답글 수정 했습니다'
	WHERE GERE_SEQ=41;
--건의 답글 삭제
DELETE FROM GEONUI_REPLY
	WHERE GERE_SEQ=41;

----------------------------건의사항 끝---------------------------


----------------------------채팅 시작---------------------------

--채팅방 목록 조회
SELECT CHRO_ID ,CHRO_CLAS_ID ,CHRO_TITLE ,CHRO_DATE 
	FROM CHAT_ROOM cr  JOIN CHAT_USER cu ON cr.CHRO_ID =cu.CHUS_CHRO_ID 
	WHERE CHUS_ACCOUNT_ID ='TMTD1';

--채팅방 조회
SELECT CHRO_ID ,CHRO_CLAS_ID ,CHRO_TITLE ,CHRO_CHAT_LOG ,CHRO_DATE 
	FROM CHAT_ROOM cr 
	WHERE CHRO_ID ='CR230906002';
--채팅방 참가자 정보 조회
SELECT CHUS_SEQ ,CHUS_CHRO_ID ,CHUS_ACCOUNT_ID ,CHUS_TYPE, USER_NICKNAME
	FROM CHAT_USER cu JOIN USER_PROFILE up ON cu.CHUS_ACCOUNT_ID = up.USER_ACCOUNT_ID 
	WHERE CHUS_CHRO_ID ='CR230906001';
--채팅방 생성
INSERT INTO CHAT_ROOM
(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)
VALUES('CR230907001', 1000000054, '채팅방 테스트', '', CURRENT_DATE);
--채팅방 참가자 추가
INSERT INTO CHAT_USER
(CHUS_SEQ, CHUS_CHRO_ID, CHUS_ACCOUNT_ID, CHUS_TYPE)
VALUES(CHUS_SEQ.NEXTVAL, 'CR230907001', 'TMTD1', 'M');
--채팅 저장하기
UPDATE CHAT_ROOM
SET CHRO_CHAT_LOG='HI'
WHERE CHRO_ID='CR230907001';
--채팅방 삭제
DELETE FROM CHAT_ROOM
	WHERE CHRO_ID='CR230907001';
--채팅방에 있는 모든 참가자 삭제
DELETE FROM CHAT_USER cu 
	WHERE CHUS_CHRO_ID ='CR230907001';
--채팅 참가자 퇴장
DELETE FROM CHAT_USER
	WHERE CHUS_SEQ='40';

SELECT 	CLAS_ID, CLAS_TITLE, CLAS_STATUS, CLAS_HYEONJAE_INWON
	FROM CLASS
	WHERE CLAS_ID = 1000000041;

SELECT CLCH_ID ,CLCH_ACCOUNT_ID ,CLCH_CLAS_ID ,CLCH_STATUS ,CLCH_YEOKAL 
	FROM CLASS_CHAMYEOJA cc 
	WHERE CLCH_CLAS_ID =1000000041;

INSERT INTO CHAT_ROOM
(CHRO_ID, CHRO_CLAS_ID, CHRO_TITLE, CHRO_CHAT_LOG, CHRO_DATE)
VALUES('CR230906' || TO_CHAR(CHRO_SEQ.NEXTVAL), 11, '#{chroTitle}', NULL, CURRENT_DATE);

--유저 정보 조회
SELECT USER_ACCOUNT_ID ,USER_NAME ,USER_NICKNAME ,USER_PROFILE_FILE 
	FROM USER_PROFILE up  
	WHERE USER_AUTH ='I';

----------------------------채팅 끝---------------------------

----------------------------알림 시작---------------------------

--알림 목록 조회
SELECT ALAR_ID,ALAR_CONTENT ,ALAR_EVENTDATE ,ALAR_REPLY_SEQ ,ALAR_CHECKED 
	FROM ALARM a 
	WHERE ALAR_ACCOUNT_ID ='TMTD1';
--알림 저장
INSERT INTO ALARM (ALAR_ID,ALAR_ACCOUNT_ID ,ALAR_CONTENT ,ALAR_EVENTDATE ,ALAR_REPLY_SEQ ,ALAR_CHECKED )
	VALUES('AL230907001','TMTD1','알림 입력 테스트',CURRENT_DATE,NULL,'N');
--알림 삭제
DELETE FROM ALARM
WHERE ALAR_ID='AL230907001';
--알림 확인
UPDATE ALARM
SET ALAR_CHECKED='Y'
WHERE ALAR_ID ='AL230907001';

----------------------------알림 끝---------------------------




