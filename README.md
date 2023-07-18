# reservation-services

> 예매서비스 구축

## Project Setting

```bash
 docker run --name gd-mysql -e MYSQL_DATABASE=reservation -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql:latest
```
```bash
 docker run -p 6379:6379 redis -p 6379:6379
```

## Project Convention

### Presentation
* API Path
  * performanceId를 API Path에 사용하는 것
  * RequestBody에 넣지 않고 Id의 경우에는 Path에 활용을 권장
* `@ResponseStatus()` 활용
  * ResponseEntity를 사용하지 않는 이유는, CommonResponse, ObjectResponse 객체로 너무 길어지기 때문

### Application

* Exception 처리
  * CustomException 사용을 지양하고, 표준 예외 사용을 권장한다.
  * 표준 예외 사용시 message는 일관성을 부여하기 위해 application 계층에 ErrorCode를 활용한다.
  * [Exception 참고1], [Exception 참고2]

### Repository

* Spring에서 정렬 및 페이징으로 제공하는 PageRequest를 활용한다.
  * PagingRequest에서 PageRequest로 변환하여 Repository로 전달한다.
* QueryDSL 활용
  * JPARepository를 기본으로 활용한다.
  * 다만, JPA에서 지원하지 않거나 QueryDSL이 유의미한 성능차이가 존재할 경우에 QueryDSL을 사용한다.

### Common
* 메소드명에 도메인을 명시하지 않는다.
  * DomainXXX.createDomain() > DomainXXX.create()
  * 클래스로 인해서 어떤 도메인에 대한 내용인지 파악이 가능하기 때문이다.
* DTO 객체 활용 규칙
  * API 별로 각각의 객체들을 활용한다. (객체를 공유하지 않는다.)
  * **Controller에서 받을 request 객체**
    * Naming: XXXYYYRequest
  * **Service에서 받을 value 객체**
    * request 객체로부터 생성된다. (ex. `reqest.toVale()`)
    * 생성자로 request 파라미터를 받지 않는 이유
      * application 계층이 presentation 계층을 의존하지 않기 때문이다.
      * presentation 계층은 application 계층에 의존해서 import 가능하다.
    * Naming: XXXYYYValue
  * **Service에서 보낼 value 객체**
    * Entity를 날리는건 어떤지?
      * presentation 계층에서 domain 계층을 의존하지 않아야 해서 부적절하다.
      * 또한, Entity에 비즈니스 로직이 담기는데 Controller까지 가면 Controller에서 비즈니스로직을 활용하려는 시도가 생길 수 있어서 원천차단의 목적
    * Repository에서 가져온 Entity를 value객체로 변환해서 전달한다.
    * Enum 타입은 domain 계층의 enum을 그대로 사용한다.
    * Naming: XXXYYYResult
  * **Controller에서 보낼 response 객체**
    * 생성자의 파라미터로 Service에서 보내는 value 객체를 받아서 객체를 생성한다.
    * Enum 타입은 String으로 받는다.
      * Enum 타입은 domain 계층에 존재하기 때문에 presentation 계층에서 의존하지 않기에 import가 안된다.
    * Naming: XXXYYYResponse

## Project Document

* [ERD](ERD.drawio)  
* [유즈케이스](./docs/usecase.md)  
* [API Docs](./docs/api.md)

## Project Policy

* 좌석 중복 선택 방지 정책 (동시성 제어)
  * 공연 등록 시점
    * 공연에 예매할 수 있는 좌석들을 Seat 테이블에 먼저 등록
  * 좌석 선택 시점
    * 1번) RedisLock 활용
      * key를 가지고 Redis에 등록하면서 Lock을 건다.
      * 다른 트랜잭션에서는 해당 key가 Lock이 걸려있으면 Exception
      * Lock을 건 트랜잭션에서 동작이 끝나면 Lock 해제
      * 이후에는 해당 좌석 정보가 이미 예약되었는지를 확인
    * 2번) DB Lock (for update) 활용 - 비관적 동시성 제어
      * 한 사용자가 데이터를 읽는 시점에 Lock을 걸고, 조회/갱신 처리가 완료될때까지 유지
  * 결제 시점
    * 선택한 좌석정보가 본인의 좌석인지 확인
* 대용량 트래픽 고민
  * 좌석 정보를 Redis에 캐싱하려고 했으나, 실시간으로 사용자들에게 보여주어야 하는 정보는 캐싱이 부적합하다고 판단
  * 공연 정보 등 변경이 자주 일어나지 않는 데이터들을 우선적으로 Redis로 캐싱
  * 추가 고려중...

<!-- 문서들링크 -->
[Exception 참고1]: https://tecoble.techcourse.co.kr/post/2020-08-17-custom-exception/
[Exception 참고2]: https://velog.io/@alsgus92/JavaAndroid-Custom-Exception%EC%9D%98-%ED%95%84%EC%9A%94%EC%84%B1%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90
