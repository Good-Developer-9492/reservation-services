package com.gd.reservationservices.common.response;


public record Paging(Long totalCount, Integer currentPage, Integer perPage, Integer totalPage) {
}
