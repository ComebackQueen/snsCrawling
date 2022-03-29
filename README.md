# SNS 크롤링(SNS Crawling)

------

**요약 : SNS(페이스북, 트위터, 인스타그램, 유튜브, 네이버 블로그) 특정 계정의 데이터들을 DB에 저장하는 프로그램**

**프로젝트 기간 : 2018/07 ~ 2018/08(현장실습)**

**Skils: Java, PostgreSQL **

---

## Description



### Summary

- SNS의 데이터들을 쉽고 빠르게 추출할 수 있도록 기획
- SNS별로 따로 DB 저장 가능
- 회사 리눅스 서버를 통하여 원하는 시간대에 자동으로 추출하게끔 설정



---

## About Project



### Program

- Json API를 통한 SNS 데이터 송수신 구현
- Properties를 통한 SNS 별 토큰, 인증키, DB ID, PW를 따로 관리(보안성)
- OAuth2.0으로 인한 여러 계정 데이터는 못가져오는 이슈



### Server

- SNS 크롤링 프로그램을 Runnable jar로 내보내기하여 리눅스에서 프로그램을 실행할 수 있게끔 설정
- 원하는 시간대에 DB 업로드 할 수 있게 설정



---

## Results

### 실행화면

![sns](https://user-images.githubusercontent.com/87848564/160561670-e2c9bd8f-f03f-451f-b99e-e46fc6e6851d.gif)






### DB현황

![sns2](https://user-images.githubusercontent.com/87848564/160561755-d5f9fbab-08fd-4253-a4ce-67c9f483b870.JPG)
