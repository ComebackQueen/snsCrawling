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

### 초기화면







### 통합검색

- **검색화면**

![search](../ComebackQueen.github.io/assets/images/README/160345008-9507d89b-6886-4cf9-b582-6ddffd7abf8f.gif)


- **상세정보**

![detail](../ComebackQueen.github.io/assets/images/README/160346907-bbc63645-3286-41b2-9d22-4ebe906442d1.gif)





### 내일로 노트 Step 1

![Step1](../ComebackQueen.github.io/assets/images/README/160348912-4370dacd-1d66-4c41-a9ec-a9d8f3a96167.gif)



### 내일로 노트 Step 2

![Step2](../ComebackQueen.github.io/assets/images/README/160349114-a0893e2b-8092-4495-b017-e48247e55e9b.gif)



### 내 주변

![map](../ComebackQueen.github.io/assets/images/README/160349614-cc11df13-a32b-4e94-bf43-487cad13b9ae.jpeg)


![detail](../ComebackQueen.github.io/assets/images/README/160349885-d8c22438-0dd4-434a-85b0-b65dd1a4616e.jpeg)