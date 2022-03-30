# Bertie
[학습용] 자동 번역 게시판

## 요약
Bertie는 Spring/Java 등 학습한 내용을 적용해보기 위해 사용되는 게시판입니다.
학습한 내용을 이 프로젝트에 적용하고, 수정하고, 정리합니다.  

## 학습내용
1. 패키지 구성
- controller / repository / service / domain / repository 를 나누어 정리
2. 예외 처리
- 사용자 정의 exception 생성 (RuntimeExeption)
- 서비스에서 예외 re-throwing
- 컨트롤러에서 try-catch
- Optional의 orElseThrow()
- Objects.requireNonNull()
3. 테스트 코드
- when / given / then 적용
- @TestPropertySource를 이용한 테스트 properties 적용
4. 설정파일
- 인터페이스를 반환하는 Bean 수동 등록
- 인터페이스를 다른 Bean에 주입
5. API
- HttpHeaders를 이용한 header 설정
- HttpEntity를 이용한 request 설정
- restTmeplate를 이용한 request 
- responseEntity를 이용한 response 처리
