package org.sto.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sto.service.AnalyticsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsResourse {

    @Autowired
    private  AnalyticsService analyticsService;
    @GetMapping("/getIncomeBetweenDates")
    @PreAuthorize("hasRole('ADMIN')")
    public BigDecimal getIncomeBetweenDates(final @RequestParam LocalDateTime startDate,
                                            final @RequestParam LocalDateTime endDate) {
        return analyticsService.countIncomeBetweenDates(startDate, endDate);
    }
}
