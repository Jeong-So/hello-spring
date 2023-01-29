# 라이브러리 살펴보기
Gradle은 의존관계가 있는 라이브러리를 함께 다운로드 한다.

"스프링 부트 라이브러리"
+ spring-boot-starter-web
  + spring-boot-starter-tomcat: 톰캣 (웹서버)
  + spring-webmvc: 스프링 웹 MVC
+ spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
+ spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
  + spring-boot
    + spring-core
  + spring-boot-starter-logging
    + logback, slf4j
    
"테스트 라이브러리"
+ spring-boot-starter-test 
  + junit: 테스트 프레임워크
  + mockito: 목 라이브러리
  + assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  + spring-test: 스프링 통합 테스트 지원


# 스프링 웹 개발 기초
웹을 개발한다는 것은 3가지 방법이 있음

+ 정적 컨텐츠
  + 서버에서 하는 것 없이 파일을 웹 브라우저에 내려줌
+ MVC와 템플릿 엔진
  + MVC : Model, View, Controller
  + View : 화면을 그리는데 집중
  + Model/Controller : 내부 비즈니스 로직(서비스) 처리에 집중
  + HTML을 그냥 주는게 아닌 서버에서 프로그래밍해서 동적으로 바꿔서 브라우저에 내려줌
+ API
  + 안드로이드/아이폰 클라이언트와 개발 시 
  + JSON이라는 DATA 포멧으로 클라이언트에 데이터 전달, 화면은 클라이언트가 알아서 그fla
  + 서버끼리 통신할 때
  + 객체를 반환