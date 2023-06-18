package com.gd.reservationservices.presentation.response;


public record Paging(Long totalCount, Integer currentPage, Integer perPage, Integer totalPage) {
}
