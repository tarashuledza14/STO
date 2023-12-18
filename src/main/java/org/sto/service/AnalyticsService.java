package org.sto.service;

import org.sto.entity.CarOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public interface AnalyticsService {
    BigDecimal countIncomeBetweenDates(final LocalDateTime startDate, final LocalDateTime endDate);
}
