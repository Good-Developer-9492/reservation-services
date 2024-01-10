package com.gd.reservationservices;

import com.gd.reservationservices.domain.BaseTimeEntity;
import com.gd.reservationservices.domain.payment.Point;
import com.gd.reservationservices.domain.performance.repository.PointRepository;
import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.domain.user.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

public class EasyRandomTest extends IntegrationTestSupport {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PointRepository pointRepository;

    @Test
    void pointTestData() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Point point = createPoint(i);
            if (point.getValue() < 0) {
                point.setType(Point.Type.USED);
            }

            if (point.getValue() == 0) {
                continue;
            }

            points.add(point);
        }

        pointRepository.saveAll(points);
    }

    @Test
    void test1() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            users.add(createUser(i));
        }
        userRepository.saveAll(users);
    }

//docker run --name redis -d -p 6379:6379 redis
    public Point createPoint(int seed) {
        Predicate<Field> id = named("id")
                .and(ofType(Long.class))
                .and(inClass(Point.class));

        Predicate<Field> createdAt = named("createdAt")
                .and(ofType(LocalDateTime.class))
                .and(inClass(BaseTimeEntity.class));

        Predicate<Field> updatedAt = named("updatedAt")
                .and(ofType(LocalDateTime.class))
                .and(inClass(BaseTimeEntity.class));

        Predicate<Field> userId = named("userId")
                .and(ofType(Long.class))
                .and(inClass(Point.class));

        Predicate<Field> type = named("type")
                .and(ofType(Point.Type.class))
                .and(inClass(Point.class));

        Predicate<Field> value = named("value")
                .and(ofType(Long.class))
                .and(inClass(Point.class));

        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.seed(seed)
                .excludeField(id)
                .excludeField(createdAt)
                .excludeField(updatedAt)
                .randomize(userId, () -> new Random().nextLong(1, 1000))
                .randomize(type, () -> Point.Type.ADD)
                .randomize(value, () -> 5000L);
//                .randomize(value, () -> new Random().nextLong(-100, 100));

        return new EasyRandom(parameters).nextObject(Point.class);
    }

    public User createUser(int seed) {
        Predicate<Field> id = named("id")
                .and(ofType(Long.class))
                .and(inClass(User.class));

        Predicate<Field> createdAt = named("createdAt")
                .and(ofType(LocalDateTime.class))
                .and(inClass(BaseTimeEntity.class));

        Predicate<Field> updatedAt = named("updatedAt")
                .and(ofType(LocalDateTime.class))
                .and(inClass(BaseTimeEntity.class));

        Predicate<Field> age = named("age")
                .and(ofType(Integer.class))
                .and(inClass(User.class));

        Predicate<Field> name = named("name")
                .and(ofType(String.class))
                .and(inClass(User.class));

        Predicate<Field> role = named("role")
                .and(ofType(Role.class))
                .and(inClass(User.class));

        Predicate<Field> phone = named("phone")
                .and(ofType(String.class))
                .and(inClass(User.class));

        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(seed)
                .excludeField(id)
                .excludeField(createdAt)
                .excludeField(updatedAt)
                .randomize(age, () -> new Random().nextInt(20, 80))
                .randomize(name, () -> new NameRandomizer().getRandomValue())
                .randomize(role, () -> Role.CUSTOMER)
                .randomize(phone, () -> new PhoneRandomizer().getRandomValue());

        return new EasyRandom(parameters).nextObject(User.class);
    }

    private static class NameRandomizer implements Randomizer<String> {
        List<String> first = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양", "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금", "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> last = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박", "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준", "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비", "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼", "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠", "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");

        @Override
        public String getRandomValue() {
            Collections.shuffle(first);
            Collections.shuffle(last);

            return first.get(0) + last.get(0) + last.get(1);
        }
    }

    private static class PhoneRandomizer implements Randomizer<String> {
        @Override
        public String getRandomValue() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                int number = new Random().nextInt(0, 9);
                stringBuilder.append(number);
            }

            return "010" + stringBuilder;
        }
    }

}
