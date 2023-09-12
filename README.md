# tamuntadab_pro

# 타문타답 프로젝트와 연결된 데이터베이스 사용법

- 운영체제에 해당하는 Docker 데스크탑 프로그램을 설치한 후 실행시킵니다.
- 설치한 데스크탑 어플리케이션에 Docker 계정으로 접속합니다.
- 명령 프롬프트를 실행한 후 다음 순서에 따릅니다.
- 도커에 로그인 합니다  					==>> docker login 
- 최신 DB를 담은 오라클 이미지를 가져옵니다 	==>> docker pull loveluv777/tmtd:1.6 
- 이미지를 컨테이너에 올린 후 실행시킵니다  	==>> docker run -itd -p 1515:1521 --name tmtd-container loveluv777/tmtd:1.6