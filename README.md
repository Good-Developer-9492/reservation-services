# reservation-services

> 예매서비스 구축

## Project Setting

```bash
 docker run --name gd-mysql -e MYSQL_DATABASE=reservation -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql:latest
```

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
