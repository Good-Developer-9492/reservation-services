package com.gd.reservationservices.application.performance.event;

import com.gd.reservationservices.domain.performance.LockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class RedisLockListener {
    private final LockRepository lockRepository;

    public RedisLockListener(LockRepository lockRepository) {
        this.lockRepository = lockRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void unLockEvent(String lockKey) {
        log.info("unlock event");
        lockRepository.unlock(lockKey);
    }
}
