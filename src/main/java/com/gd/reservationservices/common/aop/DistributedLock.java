package com.gd.reservationservices.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    String key();

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 락을 기다리는 시간
     * 락 획득을 위해 waitTime 만큼 대기
     */
    long waitTime() default 5L;

    /**
     * 락 소유 시간
     * 락을 획득한 이후 leaseTime 이 지나면 락을 해제
     */
    long leaseTime() default 1L;
}
