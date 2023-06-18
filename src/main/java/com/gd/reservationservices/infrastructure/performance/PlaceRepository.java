package com.gd.reservationservices.infrastructure.performance;

import com.gd.reservationservices.domain.performance.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
