# API 명세

## User

### POST request create user
```
POST http://localhost:8080/users
Content-Type: application/json

{
    "userId": "user01",
    "userPw": "user123456789",
    "name": "user name1",
    "age": 18,
    "email": "user@email.com",
    "phone": "01012345678",
    "role": "BUSINESS"
}
```

### GET request find user success
```
GET http://localhost:8080/users/1
Accept: application/json
```

### GET request find user not found
```
GET http://localhost:8080/users/9999999999
Accept: application/json
```

### PUT request update user
```
PUT http://localhost:8080/users
Content-Type: application/json

{
    "id": "1",
    "userPw": "user123456789",
    "name": "change name",
    "age": 19
}
```

## Performance

### POST request create performance
```
POST http://localhost:8080/performance
Content-Type: application/json

{
    "placeId": 1,
    "category": "MUSICAL",
    "startAt": "2023-06-25 12:00:00",
    "endAt": "2023-06-25 14:00:00",
    "startReservationAt": "2023-06-20 00:00:00",
    "endReservationAt": "2023-06-24 23:59:59",
    "title": "태권v",
    "content": "지구를 지킨다.",
    "acting": "로봇1",
    "filmRating": "ALL"
}
```

### GET request find performance information
```
GET http://localhost:8080/performance/business/1
Accept: application/json
```

## Reservation

### 공연 예매
```
POST http://localhost:8080/customer/performances/1/reservations
Content-Type: application/json

{
    "userId": 1,
    "seatLocation": A,
    "seatNumber": 1
}
```

### 예매 목록 조회
```
GET http://localhost:8080/business/performances/1/reservations?page=1&perPage=10
```

### 예매 상세 조회
```
GET http://localhost:8080/business/performances/1/reservations/1
```

## Coupon

### POST 쿠폰 생성
```
POST http://localhost:8080/business/coupons
Content-Type: application/json

{
    "performanceId": 1,
    "type": "PERCENT",
    "value": 10,
    "expiredAt": "2022-06-30 00:00:00",
    "amount": 10
}
```

### GET 쿠폰 단건 조회
```
GET http://localhost:8080/business/coupons/1130
Accept: application/json
```

### PUT 쿠폰 수정
```
PUT http://localhost:8080/business/coupons/1130
Content-Type: application/json

{
    "performanceId": 1,
    "type": "PERCENT",
    "value": 50,
    "expiredAt": "2022-06-30 00:00:00"
}
```

### PUT 쿠폰 사용
```
PUT http://localhost:8080/customer/coupons/use/1130
Content-Type: application/json
```

### DELETE 쿠폰 삭제
```
DELETE http://localhost:8080/business/coupons/1130
Content-Type: application/json
```
