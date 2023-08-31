package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.domain.performance.Place;

public class PlaceFixture {

    static Place create(){
        return new Place("공연장 이름", "공연장 주소", 500);
    }
}
