# Bertie
[학습용] 자동 번역 게시판  
&nbsp;  

## 요약
Bertie는 Spring/Java 등 학습한 내용을 적용해보기 위해 사용되는 게시판입니다.
학습한 내용을 이 프로젝트에 적용하고, 수정하고, 정리합니다.  
&nbsp;  

## 기능
- 회원가입
- 로그인
- 글쓰기
- 글읽기
- 번역된 글로 작성하기  
&nbsp;  

## 학습내용
1. 패키지 구성
- controller / repository / service / domain / repository 를 나누어 정리  
&nbsp;  

2. 예외 처리
- 사용자 정의 exception 생성(RuntimeExeption)  
명료한 Exception 확인 및 처리
- 서비스에서 예외 re-throwing, 컨트롤러에서 try-catch  
컨트롤러에서 예외에 따른 response 처리
- Optional의 orElseThrow()  
try-catch를 줄이기 위한 Optinal 활용
- Objects.requireNonNull(obj.get())  
obj.get()의 반환 객체가 null이면 NullPointerException  
&nbsp;  

3. 테스트 코드
- when / given / then을 적용한 테스트코드 작성
- @TestPropertySource를 이용한 테스트용 properties 적용  
&nbsp;  

4. 설정파일
- 인터페이스를 반환하는 Bean 수동 등록
- 인터페이스를 다른 Bean에 주입  
&nbsp;  

5. API
- HttpHeaders를 이용한 header 설정
- HttpEntity를 이용한 request 설정
- restTmeplate를 이용한 request 
- responseEntity를 이용한 response 처리  
&nbsp;  

6. 세션
- HttpSession을 이용한 세션 등록  
사용자 식별 및 로그인 유지를 위한 세션 등록

