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
  + JSON이라는 DATA 포멧으로 클라이언트에 데이터 전달, 화면은 클라이언트가 알아서 그림
  + 서버끼리 통신할 때
  + 객체를 반환


# 회원 관리 예제 - 백엔드 개발
일반적인 웹 애플리케이션 계층 구조
+ 컨트롤러: 웹 MVC의 컨트롤러 역할
+ 서비스: 핵심 비즈니스 로직 구현 (EX 회원은 중복 가입 불가 등)
+ 도메인(Entity): 회원, 주문 쿠폰 처럼 DB에 저장되고 관리되는 비즈니스 도메인 객체
+ 리포지토리: 비즈니스 도메인 객체를 가지고 핵심 비즈니스 로직(서비스)가 동작하도록 구현한 계층, DB에 접근, 도메인 객체를 DB에 저장하고 관리


# 스프링 빈과 의존관계
스프링 빈을 등록하는 2가지 방법
+ 컴포넌트 스캔과 자동 의존관계 설정
  + 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록(유일하게 하나만 등록해서 공유). 따라서 같은 스프링 빈이면 모두 같은 인스턴스이다.
  + '@Component' 애노테이션이 있으면 스프링 빈으로 자동 등록
  + '@Controller' 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.
  + '@Component' 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.
    + @Controller
    + @Service
    + @Repository
  + 생성자에 @Autowired를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다.
    + 생성자가 1개만 있으면 @Autowired 는 생략할 수 있다.
  + 컴포넌트 스캔은 main함수가 속한 패키지와 그 하위 패키지만 스캔하여 스프링 빈을 등록
+ 자바 코드로 직접 스프링 빈 등록하기
  + DI에는 1.필드 주입 2.setter 주입 3.생성자 주입 이렇게 3가지 방법이 있다.
    + 생성자 주입
    ```
    private final MemberService memberService;
    
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    ```
    + 필드 주입
    ```
    @Autowired private MemberService memberService;
    ```
      + 별로 안좋음 : 중간에 바꿀 수 있는 방법이 없음
    + setter 주입
    ```
    private MemberService memberService;
    
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    ```
      + 단점: MemberController 호출 시 public으로 열려있어야 함/ 중간에 바꿀 이유가 없음에도 public하게 노출됨
  + 의존관계 실행 중에 동적으로 변하는 경우가 (거의, 실제 아예) 없으므로 생성자 주입 권장
  + 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용. 그리고 정형화되지 않거나 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록
    + step1. 아직 확정되지 않은 설계 : 아직 데이터 저장소 선정되지 않음 
    + step2. 구현 초기에 interface 설계하고 구현체로 MemoryMemberRepository를 씀
    + step3. 추후 다른 리포지토리로 바꿔치기 해야함
    + step4. 기존에 운영 중인 코드 하나도 손대지 않고 바꿔치기 할 수 있는 방법 있음
      + 방법
    ```
    @Configuration
    public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    
    // return new MemoryMemberRepository(); 만 return new DbmemberRepository();로 바꿔주면 됨
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    }
    ```
+ '@Autowired'를 통한 DI는 'helloController', 'MemberService' 등과 같이 스프링이 관리하는 객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.
+ 스프링 컨테이너, DI 관련된 자세한 내용은 스프링 핵심 원리 강의에서 설명
    