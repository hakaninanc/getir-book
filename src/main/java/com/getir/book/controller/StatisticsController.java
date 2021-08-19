package com.getir.book.controller;


import com.getir.book.service.StatisticsService;
import com.getir.book.util.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/show")
    public ResponseEntity<RestResponse<String>> show() {

        final Long totalOrderCount = statisticsService.getTotalOrderCount();
//        final Double totalOrderAmount = statisticsService.getTotalOrderAmount();
        final Integer totalPurchasedBooksCount = statisticsService.getTotalPurchasedBooksCount();
        return ResponseEntity.ok(RestResponse.of(totalPurchasedBooksCount + " - " + totalOrderCount));
    }

}
