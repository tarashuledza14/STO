package org.sto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.sto.entity.CarOrder;
import org.sto.entity.CarPart;
import org.sto.service.AnalyticsService;
import org.sto.service.CarOrderService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {
    private final CarOrderService carOrderService;

    @Override
    public BigDecimal countIncomeBetweenDates(final LocalDateTime startDate, final LocalDateTime endDate) {
        List<CarOrder> carOrderList = carOrderService.getCarOrdersBetweenDates(startDate, endDate);

        BigDecimal totalOrderPriceSum = carOrderList.stream().map(CarOrder::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCarPartsSum = carOrderList.stream()
                .flatMap(order -> order.getCarParts().stream())
                .map(CarPart::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return  totalOrderPriceSum.subtract(totalCarPartsSum);
    }
}
