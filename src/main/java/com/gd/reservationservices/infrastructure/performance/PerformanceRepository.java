package com.gd.reservationservices.infrastructure.performance;

import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.infrastructure.performance.custom.PerformanceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long>, PerformanceRepositoryCustom {
}
