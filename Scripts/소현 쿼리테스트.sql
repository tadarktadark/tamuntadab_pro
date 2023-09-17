--TDF019	TDW55411	필기 검색
SELECT PIBO_ID AS id, USER_NICKNAME AS nickname, CLAS_TITLE AS classname, PIBO_CONTENT AS content, c2.CLAS_SUBJECT_JEONGBO AS subject
	FROM PILGI_BOARD JOIN USER_PROFILE
	ON PIBO_WRITER_ID = USER_ACCOUNT_ID
	JOIN CLASS c1
	ON PIBO_CLAS_ID = c1.CLAS_ID
	JOIN (SELECT CLAS_ID, REPLACE((SELECT JSON_ARRAYAGG(s.SUBJ_CODE) 
						     FROM JSON_TABLE(CLAS_SUBJECT_JEONGBO, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt 
						     JOIN (SELECT SUBJ_ID, SUBSTR(SUBJ_CODE, 3, LENGTH(SUBJ_CODE)-4) SUBJ_CODE
									FROM (SELECT SUBJ_ID, (SELECT JSON_ARRAYAGG(s1.SUTA_TITLE) 
														     FROM JSON_TABLE(SUBJ_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt1 
														     JOIN SUBJECT_TAG s1 
														     ON jt1.SUBJECT_CODE = s1.SUTA_CODE) AS SUBJ_CODE
											FROM SUBJECT)) s 
						     ON jt.SUBJECT_CODE = s.SUBJ_ID), '\', '') AS CLAS_SUBJECT_JEONGBO
			FROM CLASS) c2
	ON c2.CLAS_ID = c1.CLAS_ID;

--TDF019	TDW55412	필기 목록 조회
-- 총 게시물 개수
SELECT COUNT(*)
	FROM (SELECT PIBO_ID
			FROM PILGI_BOARD
			WHERE PIBO_STATE IN ('N', 'P')
				AND PIBO_VIEW_GROUP = 'C'
				AND PIBO_CLAS_ID IN (SELECT CLCH_CLAS_ID
										FROM CLASS_CHAMYEOJA
										WHERE CLCH_ACCOUNT_ID='TMTD2')
		UNION ALL
		SELECT PIBO_ID
			FROM PILGI_BOARD
			WHERE PIBO_STATE IN ('N', 'P')
				AND PIBO_VIEW_GROUP = 'A');

-- 게시물 목록 조회
--TDF019	TDW55413	필기 정렬
SELECT ID, ACCOUNT_ID , TITLE, REPLY_COUNT, CLAS_SUBJECT_JEONGBO AS SUBJECT_CODE, LIKE_USER, LIKE_COUNT, VIEW_COUNT, 
	CASE TO_CHAR(PIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(PIBO_REGDATE, 'HH24:MI') ELSE TO_CHAR(PIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE
	FROM (SELECT ID, ACCOUNT_ID , TITLE, REPLY_COUNT, PIBO_CLAS_ID, LIKE_USER, LIKE_COUNT, VIEW_COUNT, PIBO_REGDATE,PIBO_STATE,
			ROW_NUMBER() OVER(ORDER BY PIBO_REGDATE DESC, ID DESC) rn
			FROM (SELECT PIBO_ID AS ID, USER_NICKNAME AS ACCOUNT_ID , CLAS_TITLE || ' [' || PIBO_REPLY_COUNT || ']' AS TITLE, 
						PIBO_REPLY_COUNT AS REPLY_COUNT, PIBO_CLAS_ID, 
						CASE NVL(JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER, 
						PIBO_LIKE_COUNT AS LIKE_COUNT, PIBO_VIEW_COUNT AS VIEW_COUNT, 
						PIBO_REGDATE,
						PIBO_STATE
					FROM PILGI_BOARD JOIN USER_PROFILE 
					ON PIBO_WRITER_ID = USER_ACCOUNT_ID 
					JOIN CLASS
					ON PIBO_CLAS_ID = CLAS_ID
					WHERE PIBO_STATE IN ('N', 'P')
						AND PIBO_VIEW_GROUP = 'C'
						AND PIBO_CLAS_ID IN (SELECT CLCH_CLAS_ID
												FROM CLASS_CHAMYEOJA
												WHERE CLCH_ACCOUNT_ID='TMTD2')
				UNION ALL
				SELECT PIBO_ID AS ID, USER_NICKNAME AS WRITER_ID , CLAS_TITLE || ' [' || PIBO_REPLY_COUNT || ']' AS TITLE, 
						PIBO_REPLY_COUNT AS REPLY_COUNT, PIBO_CLAS_ID, 
						CASE NVL(JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD2'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER, 
						PIBO_LIKE_COUNT AS LIKE_COUNT, PIBO_VIEW_COUNT AS VIEW_COUNT, 
						PIBO_REGDATE,
						PIBO_STATE
					FROM PILGI_BOARD JOIN USER_PROFILE 
					ON PIBO_WRITER_ID = USER_ACCOUNT_ID 
					JOIN CLASS
					ON PIBO_CLAS_ID = CLAS_ID
					WHERE PIBO_STATE IN ('N', 'P')
						AND PIBO_VIEW_GROUP = 'A'))
	JOIN (SELECT CLAS_ID, REPLACE((SELECT JSON_ARRAYAGG(s.SUBJ_CODE) 
						     FROM JSON_TABLE(CLAS_SUBJECT_JEONGBO, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt 
						     JOIN (SELECT SUBJ_ID, SUBSTR(SUBJ_CODE, 3, LENGTH(SUBJ_CODE)-4) SUBJ_CODE
									FROM (SELECT SUBJ_ID, (SELECT JSON_ARRAYAGG(s1.SUTA_TITLE) 
														     FROM JSON_TABLE(SUBJ_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt1 
														     JOIN SUBJECT_TAG s1 
														     ON jt1.SUBJECT_CODE = s1.SUTA_CODE) AS SUBJ_CODE
											FROM SUBJECT)) s 
						     ON jt.SUBJECT_CODE = s.SUBJ_ID), '\', '') AS CLAS_SUBJECT_JEONGBO
			FROM CLASS)
	ON PIBO_CLAS_ID = CLAS_ID
	WHERE RN BETWEEN 1 AND 10
	ORDER BY rn;
--ORDER BY REGDATE DESC, ID DESC
--ORDER BY LIKE_COUNT DESC, ID DESC;
--ORDER BY VIEW_COUNT DESC, ID DESC;
--ORDER BY REPLY_COUNT DESC, ID DESC;

--TDF019	TDW55414	필기 상세 조회
SELECT PIBO_ID AS ID, USER_NICKNAME AS ACCOUNT_ID , CLAS_TITLE || ' [' || PIBO_REPLY_COUNT || ']' AS TITLE, 
		PIBO_CONTENT AS CONTENT, PIBO_REPLY_COUNT AS REPLY_COUNT, c2.CLAS_SUBJECT_JEONGBO AS SUBJECT_CODE, 
		CASE NVL(JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD2'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER, 
		PIBO_LIKE_COUNT AS LIKE_COUNT, PIBO_VIEW_COUNT AS VIEW_COUNT, 
		CASE WHEN (PIBO_DOWNLOAD_GROUP='C'
					AND NOT PIBO_CLAS_ID IN (SELECT CLCH_CLAS_ID
												FROM CLASS_CHAMYEOJA
												WHERE CLCH_ACCOUNT_ID='TMTD2'
													AND CLCH_STATUS='C'))
			OR (PIBO_DOWNLOAD_GROUP='I'
					AND NOT PIBO_WRITER_ID='TMTD2')
			THEN '0' ELSE '1' END AS DOWNLOAD_GROUP,
		CASE TO_CHAR(PIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(PIBO_REGDATE, 'HH24:MI')ELSE TO_CHAR(PIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE, 
		CASE TO_CHAR(PIBO_UPDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(PIBO_UPDATE, 'HH24:MI')ELSE TO_CHAR(PIBO_UPDATE, 'yyyy. mm. dd') END AS "UPDATE",
		PIBO_STATE AS STATE
	FROM PILGI_BOARD JOIN USER_PROFILE 
	ON PIBO_WRITER_ID = USER_ACCOUNT_ID 
	JOIN CLASS c1
	ON PIBO_CLAS_ID = c1.CLAS_ID
	JOIN (SELECT CLAS_ID, REPLACE((SELECT JSON_ARRAYAGG(s.SUBJ_CODE) 
						     FROM JSON_TABLE(CLAS_SUBJECT_JEONGBO, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt 
						     JOIN (SELECT SUBJ_ID, SUBSTR(SUBJ_CODE, 3, LENGTH(SUBJ_CODE)-4) SUBJ_CODE
									FROM (SELECT SUBJ_ID, (SELECT JSON_ARRAYAGG(s1.SUTA_TITLE) 
														     FROM JSON_TABLE(SUBJ_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt1 
														     JOIN SUBJECT_TAG s1 
														     ON jt1.SUBJECT_CODE = s1.SUTA_CODE) AS SUBJ_CODE
											FROM SUBJECT)) s 
						     ON jt.SUBJECT_CODE = s.SUBJ_ID), '\', '') AS CLAS_SUBJECT_JEONGBO
			FROM CLASS) c2
	ON PIBO_CLAS_ID = c2.CLAS_ID
	WHERE PIBO_ID='PI202309160003';

--TDF019	TDW55415	연관 필기 목록 조회
SELECT ID, ACCOUNT_ID
	FROM (SELECT USER_NICKNAME || ' 님의 필기 [' || PIBO_REPLY_COUNT || ']' AS ACCOUNT_ID, PIBO_ID AS ID
			FROM (SELECT PIBO_ID, PIBO_WRITER_ID, PIBO_CLAS_ID, PIBO_REPLY_COUNT, PIBO_VIEW_GROUP, PIBO_STATE
						FROM PILGI_BOARD
						WHERE PIBO_VIEW_GROUP = 'A'
					UNION
					SELECT PIBO_ID, PIBO_WRITER_ID, PIBO_CLAS_ID, PIBO_REPLY_COUNT, PIBO_VIEW_GROUP, PIBO_STATE
						FROM PILGI_BOARD
						WHERE PIBO_VIEW_GROUP = 'C'
							AND PIBO_CLAS_ID IN (SELECT CLCH_CLAS_ID
													FROM CLASS_CHAMYEOJA
													WHERE CLCH_ACCOUNT_ID='TMTD1'))	
			JOIN USER_PROFILE
			ON PIBO_WRITER_ID = USER_ACCOUNT_ID
			WHERE PIBO_STATE = 'N'
				AND NOT PIBO_WRITER_ID = 'TMTD1'
				AND NOT PIBO_ID='PI202309150001'
				AND PIBO_CLAS_ID=(SELECT PIBO_CLAS_ID
									FROM PILGI_BOARD
									WHERE PIBO_ID='PI202309150001')
			ORDER BY PIBO_ID DESC)
	WHERE ROWNUM < 6;
	
--PI202309130003

--TDF019	TDW51530	필기 좋아요 조회
SELECT PIBO_LIKE_USER AS LIKE_USER
	FROM PILGI_BOARD
	WHERE PIBO_ID='PI202309130012';

--TDF019	TDW51510	필기 좋아요 업데이트
UPDATE PILGI_BOARD SET PIBO_LIKE_USER = '{"TMTD2":"20230913","TMTD3":"20230913", "TMTD1":"20230913"}', PIBO_LIKE_COUNT = 3
	WHERE PIBO_ID='PI202309130012';

--TDF019	TDW51620	필기 조회수 조회
SELECT PIBO_VIEW_USER AS VIEW_USER
	FROM PILGI_BOARD
	WHERE PIBO_ID='PI202309130012';

--TDF019	TDW51610	필기 조회수 업데이트
UPDATE PILGI_BOARD SET PIBO_VIEW_USER = '{"TMTD2":"20230913"}', PIBO_VIEW_COUNT = 1
	WHERE PIBO_ID='PI202309130012';

--TDF019	TDW55416	(마이페이지)내가 쓴 필기 목록 조회
SELECT COUNT(*)
	FROM PILGI_BOARD
	WHERE PIBO_WRITER_ID ='TMTD1';

SELECT ID, TITLE, REPLY_COUNT, LIKE_COUNT, LIKE_USER, VIEW_COUNT, 
	CASE TO_CHAR(PIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(PIBO_REGDATE, 'HH24:MI')ELSE TO_CHAR(PIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE,
	"UPDATE",PIBO_STATE AS STATE, SUBJECT_CODE
	FROM (SELECT ID, TITLE, REPLY_COUNT, LIKE_COUNT, LIKE_USER, VIEW_COUNT, PIBO_REGDATE, "UPDATE",PIBO_STATE, SUBJECT_CODE ,
			ROW_NUMBER() OVER(ORDER BY PIBO_REGDATE DESC, ID DESC) rn
			FROM (SELECT PIBO_ID AS ID, CLAS_TITLE AS TITLE, PIBO_REPLY_COUNT AS REPLY_COUNT, PIBO_LIKE_COUNT AS LIKE_COUNT, 
						CASE NVL(JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD2'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER, PIBO_VIEW_COUNT AS VIEW_COUNT,
						PIBO_REGDATE, 
						CASE TO_CHAR(PIBO_UPDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(PIBO_UPDATE, 'HH24:MI')ELSE TO_CHAR(PIBO_UPDATE, 'yyyy. mm. dd') END AS "UPDATE",
						PIBO_STATE, c2.CLAS_SUBJECT_JEONGBO AS SUBJECT_CODE
					FROM PILGI_BOARD JOIN CLASS c1
					ON PIBO_CLAS_ID = c1.CLAS_ID
					JOIN (SELECT CLAS_ID, REPLACE((SELECT JSON_ARRAYAGG(s.SUBJ_CODE) 
											     FROM JSON_TABLE(CLAS_SUBJECT_JEONGBO, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt 
											     JOIN (SELECT SUBJ_ID, SUBSTR(SUBJ_CODE, 3, LENGTH(SUBJ_CODE)-4) SUBJ_CODE
														FROM (SELECT SUBJ_ID, (SELECT JSON_ARRAYAGG(s1.SUTA_TITLE) 
																			     FROM JSON_TABLE(SUBJ_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt1 
																			     JOIN SUBJECT_TAG s1 
																			     ON jt1.SUBJECT_CODE = s1.SUTA_CODE) AS SUBJ_CODE
																FROM SUBJECT)) s 
											     ON jt.SUBJECT_CODE = s.SUBJ_ID), '\', '') AS CLAS_SUBJECT_JEONGBO
							FROM CLASS) c2
					ON PIBO_CLAS_ID = c2.CLAS_ID 
					WHERE PIBO_WRITER_ID='TMTD1'))
	WHERE rn BETWEEN 21 AND 30
	ORDER BY rn;

--TDF019	TDW55416	(마이페이지)좋아요한 필기 목록 조회
SELECT COUNT(*)
	FROM (SELECT PIBO_ID, PIBO_STATE, PIBO_LIKE_USER
				FROM PILGI_BOARD
				WHERE PIBO_VIEW_GROUP = 'A'
			UNION ALL
			SELECT PIBO_ID, PIBO_STATE, PIBO_LIKE_USER
				FROM PILGI_BOARD
				WHERE PIBO_VIEW_GROUP = 'C'
					AND PIBO_CLAS_ID IN (SELECT CLCH_CLAS_ID
										FROM CLASS_CHAMYEOJA
										WHERE CLCH_ACCOUNT_ID='TMTD1')) p
	WHERE PIBO_STATE IN ('N', 'P')
		AND NOT JSON_VALUE(p.PIBO_LIKE_USER,'$.TMTD1') IS NULL;

SELECT ID, TITLE, ACCOUNT_ID,LIKE_USER, "UPDATE",
	CASE TO_CHAR(PIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(PIBO_REGDATE, 'HH24:MI')ELSE TO_CHAR(PIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE
	FROM (SELECT ID, TITLE, ACCOUNT_ID, LIKE_USER,"UPDATE",PIBO_REGDATE,
			ROW_NUMBER() OVER(ORDER BY PIBO_REGDATE DESC, ID DESC) rn
			FROM (SELECT PIBO_ID AS ID, CLAS_TITLE AS TITLE, USER_NICKNAME AS ACCOUNT_ID, 
						CASE NVL(JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER,
						JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD1') AS "UPDATE",
						PIBO_REGDATE
					FROM PILGI_BOARD JOIN CLASS
					ON PIBO_CLAS_ID = CLAS_ID
					JOIN USER_PROFILE
					ON PIBO_WRITER_ID = USER_ACCOUNT_ID
					WHERE NOT JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD1') IS NULL
						AND PIBO_STATE IN ('N','P')
						AND PIBO_VIEW_GROUP='C'
						AND PIBO_CLAS_ID IN (SELECT CLCH_CLAS_ID
												FROM CLASS_CHAMYEOJA
												WHERE CLCH_ACCOUNT_ID='TMTD1')
				UNION ALL
				SELECT PIBO_ID AS ID, CLAS_TITLE AS TITLE, USER_NICKNAME AS ACCOUNT_ID, 
						CASE NVL(JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER,
						JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD1') AS "UPDATE",
						PIBO_REGDATE
					FROM PILGI_BOARD JOIN CLASS
					ON PIBO_CLAS_ID = CLAS_ID
					JOIN USER_PROFILE
					ON PIBO_WRITER_ID = USER_ACCOUNT_ID
					WHERE NOT JSON_VALUE(PILGI_BOARD.PIBO_LIKE_USER,'$.TMTD1') IS NULL
						AND PIBO_STATE IN ('N','P')
						AND PIBO_VIEW_GROUP='A'))
	WHERE rn BETWEEN 1 AND 10
	ORDER BY rn;

--TDF019	TDW55421	필기 작성시 기본 정보 조회
SELECT c1.CLAS_ID AS CLAS_ID,c1.CLAS_TITLE AS CLAS_TITLE, c2.CLAS_SUBJECT_JEONGBO AS CLAS_SUBJECT_JEONGBO  
	FROM CLASS c1 JOIN (SELECT CLAS_ID, REPLACE((SELECT JSON_ARRAYAGG(s.SUBJ_CODE) 
										     FROM JSON_TABLE(CLAS_SUBJECT_JEONGBO, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt 
										     JOIN (SELECT SUBJ_ID, SUBSTR(SUBJ_CODE, 3, LENGTH(SUBJ_CODE)-4) SUBJ_CODE
													FROM (SELECT SUBJ_ID, (SELECT JSON_ARRAYAGG(s1.SUTA_TITLE) 
																		     FROM JSON_TABLE(SUBJ_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt1 
																		     JOIN SUBJECT_TAG s1 
																		     ON jt1.SUBJECT_CODE = s1.SUTA_CODE) AS SUBJ_CODE
															FROM SUBJECT)) s 
										     ON jt.SUBJECT_CODE = s.SUBJ_ID), '\', '') AS CLAS_SUBJECT_JEONGBO
						FROM CLASS) c2
	ON c1.CLAS_ID = c2.CLAS_ID
	WHERE c1.CLAS_ID='1000000082';

--TDF019	TDW55420	필기 작성
INSERT INTO PILGI_BOARD(PIBO_ID, PIBO_WRITER_ID, PIBO_CLAS_ID, 
						PIBO_CONTENT, PIBO_VIEW_GROUP, PIBO_DOWNLOAD_GROUP)
			VALUES((SELECT 'PI'||TO_CHAR(CURRENT_DATE,'yyyymmdd')||LPAD(NVL(MAX(TO_NUMBER(SUBSTR(PIBO_ID,11))),0)+1,4,'0')
						FROM PILGI_BOARD
						WHERE SUBSTR(PIBO_ID,1,10)='PI'||TO_CHAR(CURRENT_DATE,'yyyymmdd')), 'TMTD2', 1000000350, 
					'TEST 콘텐츠', 'A', 'A');
				
-- 필기 작성 여부 변경
UPDATE CLASS_CHAMYEOJA SET CLCH_PILGI_STATUS = 'Y'
	WHERE CLCH_ACCOUNT_ID = 'TMTD1'
		AND CLCH_ID = '1000001530';

-- 필기 임시 저장
INSERT INTO PILGI_IMSIJEOJANG(PIIM_SEQ, PIIM_WRITER_ID, PIIM_CLAS_ID, 
							PIIM_CONTENT, PIIM_VIEW_GROUP, PIIM_DOWNLOAD_GROUP)
						VALUES(PILGI_IMSIJEOJANG_SEQ.NEXTVAL, 'TMTD2', '1000000082', 
							'필기 임시저장 테스트 데이터', 'A', 'A');
-- 필기 임시저장 목록 조회
SELECT PIIM_SEQ AS ID, SUBSTR(PIIM_CONTENT,1,15)||'...' AS CONTENT, 
	CASE TO_CHAR(PIIM_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(PIIM_REGDATE, 'HH24:MI')ELSE TO_CHAR(PIIM_REGDATE, 'yyyy. mm. dd') END AS REGDATE
	FROM PILGI_IMSIJEOJANG
	WHERE PIIM_WRITER_ID ='TMTD1'
		AND PIIM_CLAS_ID='1000000313';
	
-- 필기 임시저장 데이터 가져오기
SELECT PIIM_CONTENT AS CONTENT, PIIM_VIEW_GROUP AS VIEW_GROUP, PIIM_DOWNLOAD_GROUP AS DOWNLOAD_GROUP
	FROM PILGI_IMSIJEOJANG
	WHERE PIIM_SEQ ='1';

-- 필기 임시저장 삭제하기
DELETE FROM PILGI_IMSIJEOJANG
	WHERE PIIM_SEQ ='1';

--TDF019	TDW55420	필기 수정
UPDATE PILGI_BOARD SET PIBO_CONTENT='TEST2222', PIBO_VIEW_GROUP='C', PIBO_DOWNLOAD_GROUP='C'
	WHERE PIBO_ID='PI202309130072';

--TDF019	TDW55431	필기 임시 삭제
UPDATE PILGI_BOARD SET PIBO_STATE = 'Y'
	WHERE PIBO_ID='PI202309130072';

--TDF019	TDW55431	필기 복원
UPDATE PILGI_BOARD SET PIBO_STATE = 'N'
	WHERE PIBO_ID='PI202309130072';

--TDF019	TDW55432	필기 완전 삭제
DELETE PILGI_BOARD
	WHERE PIBO_ID='PI202309130072';

--TDF019	TDW55441	댓글 조회
SELECT COUNT(*)
	FROM (SELECT CORE_SEQ 
			FROM COMMUNITY_REPLY
			WHERE CORE_BOARD_ID = 'JI202309130056'
		UNION ALL
		SELECT SACR_SEQ 
			FROM SAKJE_COMMUNITY_REPLY
			WHERE SACR_BOARD_ID = 'JI202309130056');

SELECT SEQ, WRITER_ID, CONTENT , ROOT_SEQ, STEP, "DEPTH", CHAETAEK, REGDATE,"UPDATE", STATE
	FROM (SELECT SEQ, WRITER_ID, CONTENT , ROOT_SEQ, STEP, "DEPTH", CHAETAEK, REGDATE,"UPDATE",STATE,
				ROW_NUMBER() OVER(ORDER BY CHAETAEK DESC, ROOT_SEQ, STEP) rn
				FROM (SELECT CORE_SEQ AS SEQ, USER_NICKNAME AS WRITER_ID, 
							CASE WHEN CORE_STEP > 0 THEN LPAD(' ',1+(CORE_STEP*6)*3, '&nbsp;') || CORE_CONTENT ELSE CORE_CONTENT END AS CONTENT , 
							CASE (SELECT CORE_CHAETAEK 
								FROM COMMUNITY_REPLY
								WHERE CORE_SEQ=(SELECT CORE_ROOT_SEQ
													FROM COMMUNITY_REPLY c1
													WHERE c1.CORE_SEQ=c2.CORE_SEQ)) WHEN 'Y' THEN 0 ELSE CORE_ROOT_SEQ END AS ROOT_SEQ, 
							CORE_STEP AS STEP, CORE_DEPTH AS "DEPTH", 
							CORE_CHAETAEK AS CHAETAEK, CORE_STATE AS STATE,
							CASE TO_CHAR(CORE_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(CORE_REGDATE, 'HH24:MI')ELSE TO_CHAR(CORE_REGDATE, 'yyyy. mm. dd') END AS REGDATE,
							CASE TO_CHAR(CORE_UPDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(CORE_UPDATE, 'HH24:MI')ELSE TO_CHAR(CORE_UPDATE, 'yyyy. mm. dd') END AS "UPDATE" 
						FROM COMMUNITY_REPLY c2 JOIN USER_PROFILE
						ON CORE_WRITER_ID = USER_ACCOUNT_ID
						WHERE CORE_BOARD_ID = 'JI202309130056'
					UNION ALL
					SELECT SACR_SEQ AS SEQ, 'DEL@' AS WRITER_ID , 
							CASE WHEN SACR_STEP > 0 THEN LPAD(' ',1+(SACR_STEP*6)*3, '&nbsp;') || TO_CLOB('작성자에 의해 삭제된 글입니다.') ELSE TO_CLOB('작성자에 의해 삭제된 글입니다.') END AS CONTENT ,
							CASE (SELECT CORE_CHAETAEK 
								FROM COMMUNITY_REPLY
								WHERE CORE_SEQ=(SELECT SACR_ROOT_SEQ
													FROM SAKJE_COMMUNITY_REPLY s1
													WHERE s1.SACR_SEQ=s2.SACR_SEQ)) WHEN 'Y' THEN 0 ELSE SACR_ROOT_SEQ END AS ROOT_SEQ, 
							SACR_STEP AS STEP, SACR_DEPTH AS "DEPTH", 
							'N' AS CHAETAEK, 'Y' AS STATE,
							CASE TO_CHAR(SACR_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(SACR_REGDATE, 'HH24:MI')ELSE TO_CHAR(SACR_REGDATE, 'yyyy. mm. dd') END AS REGDATE,
							CASE TO_CHAR(SACR_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(SACR_REGDATE, 'HH24:MI')ELSE TO_CHAR(SACR_REGDATE, 'yyyy. mm. dd') END AS "UPDATE"
						FROM SAKJE_COMMUNITY_REPLY s2
						WHERE SACR_BOARD_ID = 'JI202309130056'))
	WHERE rn BETWEEN 1 AND 5;

--TDF019	TDW55441	댓글 작성
UPDATE COMMUNITY_REPLY SET CORE_STEP = CORE_STEP+1
	WHERE CORE_SEQ IN (SELECT CORE_SEQ
							FROM COMMUNITY_REPLY
							WHERE CORE_ROOT_SEQ = (SELECT CORE_ROOT_SEQ
														FROM COMMUNITY_REPLY
														WHERE CORE_SEQ = 12)
								AND CORE_STEP > (SELECT CORE_STEP
														FROM COMMUNITY_REPLY
														WHERE CORE_SEQ = 12));
UPDATE SAKJE_COMMUNITY_REPLY SET SACR_STEP = SACR_STEP+1
	WHERE SACR_SEQ IN (SELECT SACR_SEQ
							FROM SAKJE_COMMUNITY_REPLY
							WHERE SACR_ROOT_SEQ = (SELECT CORE_ROOT_SEQ
														FROM COMMUNITY_REPLY
														WHERE CORE_SEQ = 12)
								AND SACR_STEP > (SELECT CORE_STEP
														FROM COMMUNITY_REPLY
														WHERE CORE_SEQ = 12));

INSERT INTO COMMUNITY_REPLY(CORE_SEQ, CORE_BOARD_ID, CORE_WRITER_ID, CORE_CONTENT, 
						CORE_ROOT_SEQ, 
						CORE_STEP, 
						CORE_DEPTH)
			VALUES(COMMUNITY_REPLY_SEQ.NEXTVAL, 'JI202309130056', 'TMTD2', '댓글 입력 테스트', 
					(SELECT CORE_ROOT_SEQ
						FROM COMMUNITY_REPLY
						WHERE CORE_SEQ = 15), 
					(SELECT CORE_STEP+1
						FROM COMMUNITY_REPLY
						WHERE CORE_SEQ = 15), 
					(SELECT CORE_DEPTH+1
						FROM COMMUNITY_REPLY
						WHERE CORE_SEQ = 15));

--TDF019	TDW55441	댓글 수정
UPDATE COMMUNITY_REPLY SET CORE_CONTENT = '댓글 업데이트', CORE_UPDATE = CURRENT_DATE
	WHERE CORE_SEQ='14';

--TDF019	TDW55441	댓글 삭제
DELETE FROM COMMUNITY_REPLY
	WHERE CORE_SEQ='14';
INSERT INTO SAKJE_COMMUNITY_REPLY(SACR_SEQ, SACR_BOARD_ID, SACR_ROOT_SEQ, SACR_STEP, SACR_DEPTH)
				VALUES(14, 'JI202309130056', 3, 7, 1);
			
--TDF020	TDW55444	댓글 채택
UPDATE COMMUNITY_REPLY SET CORE_CHAETAEK ='Y'
	WHERE CORE_SEQ ='';
UPDATE JILMUN_BOARD SET JIBO_CHAETAEK='Y'
	WHERE JIBO_ID='JI202309130056';

--TDF020	TDW55411	질문 검색
SELECT JIBO_ID AS id, USER_NICKNAME AS nickname , JIBO_TITLE  AS title,
		JIBO_CONTENT AS content, CLAS_TITLE AS classname,
		(SELECT JSON_ARRAYAGG(s.SUTA_TITLE) 
		     FROM JSON_TABLE(JIBO_SUBJECT_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt
		     JOIN SUBJECT_TAG s ON jt.SUBJECT_CODE = s.SUTA_CODE) AS subject
	FROM JILMUN_BOARD JOIN USER_PROFILE 
	ON JIBO_WRITER_ID = USER_ACCOUNT_ID 
	LEFT JOIN CLASS
	ON JIBO_CLAS_ID = CLAS_ID;

--TDF020	TDW55412	질문 목록 조회
--TDF020	TDW55413	질문 정렬
SELECT COUNT(*)
	FROM JILMUN_BOARD
	WHERE JIBO_STATE IN ('N', 'P');
					
SELECT ID, ACCOUNT_ID , TITLE, CLAS_ID, SUBJECT_CODE, REPLY_COUNT, LIKE_USER, LIKE_COUNT, VIEW_COUNT, CHAETAEK, 
		CASE TO_CHAR(JIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JIBO_REGDATE, 'HH24:MI') ELSE TO_CHAR(JIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE
	FROM (SELECT ID, ACCOUNT_ID , TITLE, CLAS_ID, SUBJECT_CODE, REPLY_COUNT, LIKE_USER, LIKE_COUNT, VIEW_COUNT, CHAETAEK, JIBO_REGDATE, 
			ROW_NUMBER() OVER(ORDER BY JIBO_REGDATE DESC, ID DESC) rn
			FROM (SELECT JIBO_ID AS ID, USER_NICKNAME AS ACCOUNT_ID , JIBO_TITLE || ' [' || JIBO_REPLY_COUNT || ']' AS TITLE,
						CLAS_TITLE  AS CLAS_ID, JIBO_STATE AS STATE,
						(SELECT JSON_ARRAYAGG(s.SUTA_TITLE) 
						     FROM JSON_TABLE(JIBO_SUBJECT_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt
						     JOIN SUBJECT_TAG s ON jt.SUBJECT_CODE = s.SUTA_CODE) AS SUBJECT_CODE,
						JIBO_REPLY_COUNT AS REPLY_COUNT, 
						CASE NVL(JSON_VALUE(JILMUN_BOARD.JIBO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER, 
						JIBO_LIKE_COUNT AS LIKE_COUNT, JIBO_VIEW_COUNT AS VIEW_COUNT, JIBO_CHAETAEK AS CHAETAEK,
						JIBO_REGDATE,
						CASE TO_CHAR(JIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JIBO_REGDATE, 'HH24:MI') ELSE TO_CHAR(JIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE
					FROM JILMUN_BOARD JOIN USER_PROFILE 
					ON JIBO_WRITER_ID = USER_ACCOUNT_ID 
					LEFT JOIN CLASS
					ON JIBO_CLAS_ID = CLAS_ID
					WHERE JIBO_STATE IN ('N', 'P')))
	WHERE rn BETWEEN 1 AND 10
	ORDER BY rn;
--ORDER BY LIKE_COUNT DESC, ID DESC;
--ORDER BY VIEW_COUNT DESC, ID DESC;
--ORDER BY REPLY_COUNT DESC, ID DESC;

--TDF020	TDW55414	질문 상세 조회
SELECT JIBO_ID AS ID, USER_NICKNAME AS ACCOUNT_ID , JIBO_TITLE || ' [' || JIBO_REPLY_COUNT || ']' AS TITLE,
		CLAS_TITLE  AS CLAS_ID,
		(SELECT JSON_ARRAYAGG(s.SUTA_TITLE) 
		     FROM JSON_TABLE(JIBO_SUBJECT_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt
		     JOIN SUBJECT_TAG s ON jt.SUBJECT_CODE = s.SUTA_CODE) AS SUBJECT_CODE,
		JIBO_CONTENT AS CONTENT, JIBO_REPLY_COUNT AS REPLY_COUNT, 		 
		CASE NVL(JSON_VALUE(JILMUN_BOARD.JIBO_LIKE_USER,'$.TMTD2'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER, 
		JIBO_LIKE_COUNT AS LIKE_COUNT, JIBO_VIEW_COUNT AS VIEW_COUNT, JIBO_CHAETAEK AS CHAETAEK, JIBO_STATE AS STATE,
		CASE TO_CHAR(JIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JIBO_REGDATE, 'HH24:MI')ELSE TO_CHAR(JIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE, 
		CASE TO_CHAR(JIBO_UPDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JIBO_UPDATE, 'HH24:MI')ELSE TO_CHAR(JIBO_UPDATE, 'yyyy. mm. dd') END AS "UPDATE"
	FROM JILMUN_BOARD JOIN USER_PROFILE 
	ON JIBO_WRITER_ID = USER_ACCOUNT_ID 
	LEFT JOIN CLASS
	ON JIBO_CLAS_ID = CLAS_ID
	WHERE JIBO_ID='JI202309150056';

--TDF020	TDW51530	질문 좋아요 조회
SELECT JIBO_LIKE_USER AS LIKE_USER
	FROM JILMUN_BOARD
	WHERE JIBO_ID='JI202309130056';

--TDF020	TDW51510	질문 좋아요 업데이트
UPDATE JILMUN_BOARD SET JIBO_LIKE_USER = '{"TMTD1":"20230913"}', JIBO_LIKE_COUNT = 1
	WHERE JIBO_ID='JI202309130056';

--TDF020	TDW51620	질문 조회수 조회
SELECT JIBO_VIEW_USER AS VIEW_USER
	FROM JILMUN_BOARD
	WHERE JIBO_ID='JI202309130056';

--TDF020	TDW51610	질문 조회수 업데이트
UPDATE JILMUN_BOARD SET JIBO_VIEW_USER = '{"TMTD1":"20230913"}', JIBO_VIEW_COUNT = 1
	WHERE JIBO_ID='JI202309130056';

--TDF020	TDW55416	(마이페이지)내가 쓴 질문 목록 조회
SELECT COUNT(*)
	FROM JILMUN_BOARD
	WHERE JIBO_WRITER_ID ='TMTD1';
										
SELECT ID, TITLE, CLAS_ID, SUBJECT_CODE, REPLY_COUNT, LIKE_COUNT, LIKE_USER, VIEW_COUNT,CHAETAEK, 
	CASE TO_CHAR(JIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JIBO_REGDATE, 'HH24:MI')ELSE TO_CHAR(JIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE, 
	"UPDATE",STATE
	FROM (SELECT ID, TITLE, CLAS_ID, SUBJECT_CODE, REPLY_COUNT, LIKE_COUNT, LIKE_USER, CHAETAEK, JIBO_REGDATE, "UPDATE",STATE, VIEW_COUNT,
			ROW_NUMBER() OVER(ORDER BY JIBO_REGDATE DESC, ID DESC) rn
			FROM (SELECT JIBO_ID AS ID, JIBO_TITLE AS TITLE, 
						CLAS_TITLE  AS CLAS_ID,
						(SELECT JSON_ARRAYAGG(s.SUTA_TITLE) 
						     FROM JSON_TABLE(JIBO_SUBJECT_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt
						     JOIN SUBJECT_TAG s ON jt.SUBJECT_CODE = s.SUTA_CODE) AS SUBJECT_CODE,
						JIBO_REPLY_COUNT AS REPLY_COUNT, JIBO_LIKE_COUNT AS LIKE_COUNT, JIBO_VIEW_COUNT AS VIEW_COUNT,
						CASE NVL(JSON_VALUE(JILMUN_BOARD.JIBO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER,
						JIBO_CHAETAEK AS CHAETAEK, JIBO_STATE AS STATE,
						JIBO_REGDATE, 
						CASE TO_CHAR(JIBO_UPDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JIBO_UPDATE, 'HH24:MI')ELSE TO_CHAR(JIBO_UPDATE, 'yyyy. mm. dd') END AS "UPDATE"
					FROM JILMUN_BOARD LEFT JOIN CLASS
					ON JIBO_CLAS_ID = CLAS_ID
					WHERE JIBO_WRITER_ID='TMTD1'))
	WHERE rn BETWEEN 1 AND 10
	ORDER BY rn;

--TDF020	TDW55416	(마이페이지)좋아요한 질문 목록 조회
SELECT COUNT(*)
	FROM JILMUN_BOARD
	WHERE JIBO_STATE IN ('N', 'P')
		AND NOT JSON_VALUE(JILMUN_BOARD.JIBO_LIKE_USER,'$.TMTD1') IS NULL;
	
SELECT ID, TITLE, ACCOUNT_ID, LIKE_USER, "UPDATE", 
	CASE TO_CHAR(JIBO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JIBO_REGDATE, 'HH24:MI')ELSE TO_CHAR(JIBO_REGDATE, 'yyyy. mm. dd') END AS REGDATE
	FROM (SELECT ID, TITLE, ACCOUNT_ID,LIKE_USER, "UPDATE",JIBO_REGDATE,
			ROW_NUMBER() OVER(ORDER BY JIBO_REGDATE DESC, ID DESC) rn
			FROM (SELECT JIBO_ID AS ID, JIBO_TITLE AS TITLE, JIBO_WRITER_ID AS ACCOUNT_ID,
						CASE NVL(JSON_VALUE(JILMUN_BOARD.JIBO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER,
						JSON_VALUE(JILMUN_BOARD.JIBO_LIKE_USER,'$.TMTD1') AS "UPDATE",
						JIBO_REGDATE
					FROM JILMUN_BOARD
					WHERE 
						JIBO_STATE IN ('N', 'P')
						AND NOT JSON_VALUE(JILMUN_BOARD.JIBO_LIKE_USER,'$.TMTD1') IS NULL))
	WHERE rn BETWEEN 1 AND 10
	ORDER BY rn;

--TDF020	TDW55421	질문 작성 클랙스 조회
SELECT CLAS_ID, CLAS_TITLE
	FROM CLASS JOIN CLASS_CHAMYEOJA
	ON CLAS_ID = CLCH_CLAS_ID
	WHERE CLCH_ACCOUNT_ID='TMTD2'
		AND CLAS_STATUS='종료'
		AND CLCH_STATUS='C';
	
-- 질문 작성 클래스 선택시 관련 과목 조회
SELECT CLAS_ID, '['||REPLACE(REPLACE((SELECT JSON_ARRAYAGG(s1.SUBJ_CODE) 
					     FROM JSON_TABLE(CLAS_SUBJECT_JEONGBO, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt1 
					     JOIN SUBJECT s1 
					     ON jt1.SUBJECT_CODE = s1.SUBJ_ID),'[',''),']','')||']' AS CLAS_SUBJECT_JEONGBO,
				REPLACE((SELECT JSON_ARRAYAGG(s.SUBJ_CODE) 
										     FROM JSON_TABLE(CLAS_SUBJECT_JEONGBO, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt 
										     JOIN (SELECT SUBJ_ID, SUBSTR(SUBJ_CODE, 3, LENGTH(SUBJ_CODE)-4) SUBJ_CODE
													FROM (SELECT SUBJ_ID, (SELECT JSON_ARRAYAGG(s1.SUTA_TITLE) 
																		     FROM JSON_TABLE(SUBJ_CODE, '$[*]' COLUMNS (SUBJECT_CODE VARCHAR2(400) PATH '$')) jt1 
																		     JOIN SUBJECT_TAG s1 
																		     ON jt1.SUBJECT_CODE = s1.SUTA_CODE) AS SUBJ_CODE
															FROM SUBJECT)) s 
										     ON jt.SUBJECT_CODE = s.SUBJ_ID), '\', '') AS CLAS_CONTENT
	FROM CLASS
	WHERE CLAS_ID='1000000070';

--TDF020	TDW55420	질문 작성 클래스 아이디 다이나믹 쿼리
INSERT INTO JILMUN_BOARD(JIBO_ID, JIBO_WRITER_ID, JIBO_TITLE, 
						JIBO_CLAS_ID, JIBO_SUBJECT_CODE, JIBO_CONTENT)
			VALUES((SELECT 'JI'||TO_CHAR(CURRENT_DATE,'yyyymmdd')||LPAD(NVL(MAX(TO_NUMBER(SUBSTR(JIBO_ID,11))),0)+1,4,'0')
						FROM JILMUN_BOARD
						WHERE SUBSTR(JIBO_ID,1,10)='JI'||TO_CHAR(CURRENT_DATE,'yyyymmdd')), 'TMTD1', '타이틀', 
					1000000082, '["209"]', '테스트');
				
--TDF020	TDW55420	질문 수정
UPDATE JILMUN_BOARD SET JIBO_CLAS_ID = 1000000082, JIBO_SUBJECT_CODE = '["209"]', JIBO_CONTENT = '테스트'
	WHERE JIBO_ID = 'JI202309130077';
				
--TDF020	TDW55432	질문 완전 삭제
DELETE JILMUN_BOARD
	WHERE JIBO_ID='JI202309130077';

--TDF021	TDW55411	자유 검색
SELECT JABO_ID AS id, USER_NICKNAME AS nickname , JABO_TITLE AS title, JABO_CONTENT AS content
	FROM JAYU_BOARD JOIN USER_PROFILE 
	ON JABO_WRITER_ID = USER_ACCOUNT_ID;

--TDF021	TDW55412	자유 목록 조회
--TDF021	TDW55413	자유 정렬
SELECT COUNT(*)
	FROM JAYU_BOARD
	WHERE JABO_STATE IN ('N','P');

SELECT ID, ACCOUNT_ID , TITLE, REPLY_COUNT, LIKE_USER, LIKE_COUNT, VIEW_COUNT,
	CASE TO_CHAR(JABO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JABO_REGDATE, 'HH24:MI') ELSE TO_CHAR(JABO_REGDATE, 'yyyy. mm. dd') END AS REGDATE
	FROM (SELECT ID, ACCOUNT_ID , TITLE, REPLY_COUNT, LIKE_USER, LIKE_COUNT, VIEW_COUNT, JABO_REGDATE, STATE, 
			ROW_NUMBER() OVER(ORDER BY JABO_REGDATE DESC, ID DESC) rn
			FROM (SELECT JABO_ID AS ID, USER_NICKNAME AS ACCOUNT_ID , JABO_TITLE || ' [' || JABO_REPLY_COUNT || ']' AS TITLE,
						JABO_REPLY_COUNT AS REPLY_COUNT, JABO_STATE AS STATE,
						CASE NVL(JSON_VALUE(JAYU_BOARD.JABO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER, 
						JABO_LIKE_COUNT AS LIKE_COUNT, JABO_VIEW_COUNT AS VIEW_COUNT, 
						JABO_REGDATE
					FROM JAYU_BOARD JOIN USER_PROFILE 
					ON JABO_WRITER_ID = USER_ACCOUNT_ID))
	WHERE rn BETWEEN 1 AND 10
		AND STATE IN ('P','N')
	ORDER BY rn;
--ORDER BY LIKE_COUNT DESC, ID DESC;
--ORDER BY VIEW_COUNT DESC, ID DESC;
--ORDER BY REPLY_COUNT DESC, ID DESC;

--TDF021	TDW55414	자유 상세 조회
SELECT JABO_ID AS ID, USER_NICKNAME AS ACCOUNT_ID , JABO_TITLE || ' [' || JABO_REPLY_COUNT || ']' AS TITLE,
		JABO_CONTENT AS CONTENT, JABO_REPLY_COUNT AS REPLY_COUNT, 		 
		CASE NVL(JSON_VALUE(JAYU_BOARD.JABO_LIKE_USER,'$.TMTD2'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER, 
		JABO_LIKE_COUNT AS LIKE_COUNT, JABO_VIEW_COUNT AS VIEW_COUNT, JABO_STATE AS STATE,
		CASE TO_CHAR(JABO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JABO_REGDATE, 'HH24:MI')ELSE TO_CHAR(JABO_REGDATE, 'yyyy. mm. dd') END AS REGDATE, 
		CASE TO_CHAR(JABO_UPDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JABO_UPDATE, 'HH24:MI')ELSE TO_CHAR(JABO_UPDATE, 'yyyy. mm. dd') END AS "UPDATE"
	FROM JAYU_BOARD JOIN USER_PROFILE 
	ON JABO_WRITER_ID = USER_ACCOUNT_ID
	WHERE JABO_ID='JA202309160001';

--TDF021	TDW51530	자유 좋아요 조회
SELECT JABO_LIKE_USER AS LIKE_USER
	FROM JAYU_BOARD
	WHERE JABO_ID='JA202313090072';

--TDF021	TDW51510	자유 좋아요 업데이트

UPDATE JAYU_BOARD SET JABO_LIKE_USER = '{"TMTD1":"20230913"}', JABO_LIKE_COUNT = 1
	WHERE JABO_ID='JA202313090072';

--TDF021	TDW51620	자유 조회수 조회
SELECT JABO_VIEW_USER AS VIEW_USER
	FROM JAYU_BOARD
	WHERE JABO_ID='JA202313090072';

--TDF021	TDW51610	자유 조회수 업데이트
UPDATE JAYU_BOARD SET JABO_VIEW_USER = '{"TMTD1":"20230913"}', JABO_VIEW_COUNT = 1
	WHERE JABO_ID='JA202313090072';

--TDF021	TDW55416	(마이페이지)내가 쓴 자유 목록 조회
SELECT COUNT(*)
	FROM JAYU_BOARD
	WHERE JABO_WRITER_ID ='TMTD1';
										
SELECT ID, TITLE, REPLY_COUNT, LIKE_COUNT, LIKE_USER, VIEW_COUNT, 
	CASE TO_CHAR(JABO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JABO_REGDATE, 'HH24:MI')ELSE TO_CHAR(JABO_REGDATE, 'yyyy. mm. dd') END AS REGDATE, 
	"UPDATE", STATE
	FROM (SELECT ID, TITLE, REPLY_COUNT, LIKE_COUNT, LIKE_USER, VIEW_COUNT,JABO_REGDATE, "UPDATE", STATE,
			ROW_NUMBER() OVER(ORDER BY JABO_REGDATE DESC, ID DESC) rn
			FROM (SELECT JABO_ID AS ID, JABO_TITLE AS TITLE,
						JABO_REPLY_COUNT AS REPLY_COUNT, JABO_LIKE_COUNT AS LIKE_COUNT, JABO_VIEW_COUNT AS VIEW_COUNT,JABO_STATE AS STATE,
						CASE NVL(JSON_VALUE(JAYU_BOARD.JABO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER,
						JABO_REGDATE,
						CASE TO_CHAR(JABO_UPDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JABO_UPDATE, 'HH24:MI')ELSE TO_CHAR(JABO_UPDATE, 'yyyy. mm. dd') END AS "UPDATE"
					FROM JAYU_BOARD
					WHERE JABO_WRITER_ID='TMTD1'))
	WHERE rn BETWEEN 1 AND 10
	ORDER BY rn;

--TDF021	TDW55416	(마이페이지)좋아요한 자유 목록 조회
SELECT COUNT(*)
	FROM JAYU_BOARD
	WHERE JABO_STATE IN ('N', 'P')
		AND NOT JSON_VALUE(JAYU_BOARD.JABO_LIKE_USER,'$.TMTD1') IS NULL;
	
SELECT ID, TITLE, ACCOUNT_ID,LIKE_USER, "UPDATE", 
	CASE TO_CHAR(JABO_REGDATE, 'yyyymmdd') WHEN TO_CHAR(CURRENT_DATE, 'yyyymmdd') THEN TO_CHAR(JABO_REGDATE, 'HH24:MI')ELSE TO_CHAR(JABO_REGDATE, 'yyyy. mm. dd') END AS REGDATE
	FROM (SELECT ID, TITLE, ACCOUNT_ID,LIKE_USER, JABO_REGDATE, "UPDATE",
			ROW_NUMBER() OVER(ORDER BY JABO_REGDATE DESC, ID DESC) rn
			FROM (SELECT JABO_ID AS ID, JABO_TITLE AS TITLE, JABO_WRITER_ID AS ACCOUNT_ID,
						CASE NVL(JSON_VALUE(JAYU_BOARD.JABO_LIKE_USER,'$.TMTD1'), '0') WHEN '0' THEN '0' ELSE '1' END AS LIKE_USER,
						JABO_REGDATE, 
						JSON_VALUE(JAYU_BOARD.JABO_LIKE_USER,'$.TMTD1') AS "UPDATE"
					FROM JAYU_BOARD
					WHERE NOT JSON_VALUE(JAYU_BOARD.JABO_LIKE_USER,'$.TMTD1') IS NULL
						AND JABO_STATE IN ('N', 'P')))
	WHERE rn BETWEEN 1 AND 10
	ORDER BY rn;

--TDF021	TDW55421	자유 작성
INSERT INTO JAYU_BOARD(JABO_ID, JABO_WRITER_ID, JABO_TITLE, JABO_CONTENT)
		VALUES((SELECT 'JA'||TO_CHAR(CURRENT_DATE,'yyyymmdd')||LPAD(NVL(MAX(TO_NUMBER(SUBSTR(JABO_ID,11))),0)+1,4,'0')
						FROM JAYU_BOARD
						WHERE SUBSTR(JABO_ID,1,10)='JA'||TO_CHAR(CURRENT_DATE,'yyyymmdd')), 'TMTD1', '타이틀', '내용');

--TDF021	TDW55420	자유 수정
UPDATE JAYU_BOARD SET JABO_CONTENT = '테스트'
	WHERE JABO_ID = 'JA202313090072';

--TDF021	TDW55432	자유 완전 삭제
DELETE JAYU_BOARD
	WHERE JABO_ID='JA202313090072';

--TDF025	TDW55531	게시글 또는 댓글 신고
-- 신고 카테코리 조회
SELECT SICA_ID AS ID, SICA_EXPLANATION AS CATEGORY
	FROM SINGO_CATEGORY
	WHERE SICA_DELFLAG = 'N';

-- 신고 사유 입력
-- SIDA_ID 가져오기
SELECT SIDA_ID AS ID, SIDA_COUNT AS "COUNT" 
	FROM SINGO_DAESANG
	WHERE SIDA_STATE = 'N'
		AND SIDA_DAESANG_ID ='1';
	
--SIDA_ID가 없다면?
INSERT INTO SINGO_DAESANG(SIDA_ID, SIDA_DAESANG_ID)
				VALUES((SELECT 'SI'||TO_CHAR(CURRENT_DATE,'yyyymmdd')||LPAD(MAX(TO_NUMBER(SUBSTR(SIDA_ID,11)))+1,4,'0')
										FROM SINGO_DAESANG
										WHERE SUBSTR(SIDA_ID,1,10)='SI'||TO_CHAR(CURRENT_DATE,'yyyymmdd')), 'JA202313090072');
--SIDA_ID가 있다면?
UPDATE SINGO_DAESANG SET SIDA_COUNT=SIDA_COUNT+1
	WHERE SIDA_ID='SI202309130025';
	-- SIDA_COUNT가 4였다면?(신고해서 5가 된다면?)
	UPDATE SINGO_DAESANG SET SIDA_STATE = 'P'
		WHERE SIDA_ID='SI202309130025';
	-- 어디냐에 따라
	UPDATE PILGI_BOARD SET PIBO_STATE = 'P'
		WHERE PIBO_ID='';
	UPDATE JILMUN_BOARD SET JIBO_STATE = 'P'
		WHERE JIBO_ID='';
	UPDATE JAYU_BOARD SET JABO_STATE = 'P'
		WHERE JABO_ID='';
	UPDATE COMMUNITY_REPLY SET CORE_STATE = 'P'
		WHERE CORE_SEQ='17';
	
-- 신고사유 입력 - SCAT10일 경우 CONTENT도 삽입
INSERT INTO SINGO_SAYU(SISA_SEQ, SISA_ACCOUNT_ID, SISA_SIDA_ID, SISA_CATEGORY, SISA_CONTENT)
				VALUES(SINGO_SAYU_SEQ.NEXTVAL, 'TMTD1', 'SI202309130025', 'SCAT10', '기타');

-- TDF025	TDW55532 5회 신고된 게시글 조회
SELECT SIDA_ID AS ID, SIDA_DAESANG_ID AS DAESANG_ID, CORE_CONTENT AS DAESANG_CONTENT, CORE_WRITER_ID AS ACCOUNT_ID, 
		SISA_ACCOUNT_ID AS SAYU_ACCOUNT_ID, SICA_EXPLANATION AS CATEGORY , SISA_CONTENT AS CONTENT, SISA_REGDATE AS REGDATE  
	FROM SINGO_DAESANG JOIN SINGO_SAYU
	ON SIDA_ID = SISA_SIDA_ID
	JOIN SINGO_CATEGORY
	ON SISA_CATEGORY = SICA_ID
	JOIN COMMUNITY_REPLY 
	ON SIDA_DAESANG_ID=CORE_SEQ
	WHERE SIDA_STATE='P';

-- 5회 신고된 게시글 처리
UPDATE SINGO_DAESANG SET SIDA_STATE ='D'
	WHERE SIDA_ID='SI202309130025';

SELECT SIUS_SEQ AS ID, SIUS_ACCOUNT_ID AS ACCOUNT_ID , SIUS_COUNT AS "COUNT" , SIUS_STATE AS STATE
	FROM SINGO_USER
	WHERE SIUS_ACCOUNT_ID = 'TMTD141'
		AND SIUS_STATE='N';
-- 없으면
INSERT INTO SINGO_USER(SIUS_SEQ, SIUS_ACCOUNT_ID)
	VALUES(SINGO_USER_SEQ.NEXTVAL, 'TMTD159');
-- 있으면
UPDATE SINGO_USER SET SIUS_COUNT=SIUS_COUNT+1
	WHERE SIUS_SEQ='4';
-- 신고 5회 회원 정지대상 업데이트
UPDATE SINGO_USER SET SIUS_STATE='Y'
	WHERE SIUS_COUNT=5
		AND SIUS_STATE='N';

-- 전체 강의실 건물 개수 조회
SELECT COUNT(*)
	FROM GANGEUISIL_COMM
	WHERE GACO_SIDO='서울'
		AND GACO_SIGUNGU='종로구';
--TDF033	TDW59110	강의실 건물 시도 조회
SELECT GACO_SIDO || '(' || COUNT(GACO_SIDO) || ')'
	FROM GANGEUISIL_COMM
	GROUP BY GACO_SIDO
	ORDER BY GACO_SIDO;

--TDF033	TDW59110	강의실 시군구 조회
SELECT GACO_SIGUNGU || '(' || COUNT(GACO_SIGUNGU) || ')'
	FROM GANGEUISIL_COMM
	WHERE GACO_SIDO='대전'
	GROUP BY GACO_SIGUNGU;

--TDF033	TDW59110	강의실 목록 조회
SELECT GACO_ID , GACO_NAME , GACO_JUSO , GACO_LAT , GACO_LON , GACO_OPEN , GACO_CLOSE
	FROM (SELECT GACO_ID , GACO_NAME , GACO_JUSO , GACO_LAT , GACO_LON , GACO_OPEN , GACO_CLOSE,
			ROW_NUMBER() OVER(ORDER BY GACO_ID) rn
			FROM GANGEUISIL_COMM
			WHERE GACO_SIDO='서울'
			)
	WHERE rn BETWEEN 1 AND 5;
	
--TDF033	TDW59120	강의실 상세 조회
SELECT GAGA_ID, GAGA_GACO_ID AS GACO_ID, GAGA_NAME, GAGA_MAX, GAGA_HOUR_PRICE 
	FROM GANGEUISIL_GAEBYEOL
	WHERE GAGA_GACO_ID = 'GACO00001';

--TDF033	TDW59130	예약 가능한 날짜 조회
SELECT GAYE_YEYAK_DATE, GAYE_START_TIME , GAYE_HOURS 
	FROM GANGEUISIL_YEYAK
	WHERE GAYE_GAGA_ID = 'GAGA00277'
		AND GAYE_YEYAK_DATE > TO_CHAR(CURRENT_DATE ,'yyyyddmm');

--TDF033	TDW59130	예약 가능한 시간 조회
SELECT GACO_OPEN , GACO_CLOSE
	FROM GANGEUISIL_GAEBYEOL JOIN GANGEUISIL_COMM
	ON GAGA_GACO_ID = GACO_ID
	WHERE GAGA_ID='GAGA00001';

--TDF033	TDW59140	예약 정보 입력 클래스 ID가 있는 경우/없는 경우
-- 'TMTD'||LPAD(SUBSTR('TMTD00011',INSTR('TMTD00011', '0', -1)+1)+1,5,'0');
(SELECT 'YY'||TO_CHAR(CURRENT_DATE,'yyyymmdd')||LPAD(MAX(TO_NUMBER(SUBSTR(GAYE_ID,11)))+1,4,'0')
	FROM GANGEUISIL_YEYAK
	WHERE SUBSTR(GAYE_ID,1,10)='YY'||TO_CHAR(CURRENT_DATE,'yyyymmdd'));

INSERT INTO GANGEUISIL_YEYAK
			(GAYE_ID, GAYE_GAGA_ID, GAYE_ACCOUNT_ID, 
			GAYE_PHONE_NUMBER, GAYE_YEYAK_DATE, GAYE_START_TIME, 
			GAYE_HOURS, GAYE_STATE, GAYE_CLAS_ID, 
			GAYE_GYEOLJE_TYPE, GAYE_GYEOLJE_USER)
	VALUES((SELECT 'YY'||TO_CHAR(CURRENT_DATE,'yyyymmdd')||LPAD(MAX(TO_NUMBER(SUBSTR(GAYE_ID,11)))+1,4,'0')
	FROM GANGEUISIL_YEYAK
	WHERE SUBSTR(GAYE_ID,1,10)='YY'||TO_CHAR(CURRENT_DATE,'yyyymmdd')), 'GAGA00271', 'TMTD1', '01000000001', '20230913', '1400', 2, 'D' , 1000000013, 'B', '{"TMTD1":"N","TMTD2":"N","TMTD3":"N","TMTD4":"N","TMTD5":"N"}');
INSERT INTO GANGEUISIL_YEYAK
			(GAYE_ID, GAYE_GAGA_ID, GAYE_ACCOUNT_ID, 
			GAYE_PHONE_NUMBER, GAYE_YEYAK_DATE, GAYE_START_TIME, 
			GAYE_HOURS, GAYE_STATE,
			GAYE_GYEOLJE_TYPE, GAYE_GYEOLJE_USER)
	VALUES((SELECT 'YY'||TO_CHAR(CURRENT_DATE,'yyyymmdd')||LPAD(MAX(TO_NUMBER(SUBSTR(GAYE_ID,11)))+1,4,'0')
	FROM GANGEUISIL_YEYAK
	WHERE SUBSTR(GAYE_ID,1,10)='YY'||TO_CHAR(CURRENT_DATE,'yyyymmdd')), 'GAGA00271', 'TMTD1', '01000000001', '20230913', '1400', 2, 'D' , 'B', '{"TMTD1":"N","TMTD2":"N","TMTD3":"N","TMTD4":"N","TMTD5":"N"}');

-- 예약 정보 입력 후 입력한 정보의 gaye_id 값 가져오기
SELECT MAX(GAYE_ID)
	FROM GANGEUISIL_YEYAK;

-- 예약한 클래스의 학생 참여자들 ACCOUNT_ID 조회
SELECT CLCH_ACCOUNT_ID 
	FROM CLASS_CHAMYEOJA
	WHERE CLCH_CLAS_ID =1000000013
		AND CLCH_STATUS = 'Y'
		AND NOT CLCH_YEOKAL = 'I';

-- 결제 insert, 클래스원 전부 for문
(SELECT 'RE'||TO_CHAR(MAX(SUBSTR(GYEO_ID, 3))+1)
	FROM GYEOLJE);
INSERT INTO GYEOLJE
		(GYEO_ID, GYEO_GEUMAEK, GYEO_DAESANG_ID, 
		GYEO_ACCOUNT_ID, GYEO_REGDATE, GYEO_WANRYOIL, 
		GYEO_BANGBEOP, GYEO_STATUS)
	VALUES((SELECT 'RE'||TO_CHAR(MAX(SUBSTR(GYEO_ID, 3))+1)
	FROM GYEOLJE), 60000, 'YY202309080006', 
		'TDTD1', CURRENT_DATE , CURRENT_DATE+3, 
		'P', 'W' );

-- 내 예약 목록 조회(GAYE_ACCOUNT_ID가 로그인 유저랑 같다면 취소 버튼)
SELECT GAYE_ID, GAYE_GAGA_ID, GAYE_ACCOUNT_ID , GAYE_PHONE_NUMBER , GAYE_YEYAK_DATE , GAYE_START_TIME , GAYE_HOURS , GAYE_STATE , GAYE_CLAS_ID , GAYE_GYEOLJE_TYPE , GAYE_GYEOLJE_USER  
	FROM GANGEUISIL_YEYAK
	WHERE GAYE_GYEOLJE_USER LIKE '%TMTD1%';

--TDF033	TDW59150	예약 취소
UPDATE GANGEUISIL_YEYAK SET GAYE_STATE='N'
	WHERE GAYE_ID='YY2023090006';