package org.sto.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.sto.dto.CarDTO;
import org.sto.dto.CarOrderDTO;
import org.sto.dto.CarPartDTO;
import org.sto.dto.UserDTO;
import org.sto.entity.CarOrder;
import org.sto.entity.enumerable.Status;
import org.sto.service.CarOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/carOrder")
public class CarOrderResource {

    @Autowired
    private CarOrderService carOrderService;

    @PostMapping("/addOrder")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addOrder(final @RequestBody CarOrderDTO carOrderDTO ) {
        carOrderService.addCarOrder(carOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Car order added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(final @PathVariable Long id) {
        carOrderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Car order deleted successfully");
    }
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<CarOrder>> getAllOrders() {
        return ResponseEntity.ok(carOrderService.getAllOrders());
    }
    @GetMapping("/projectStatus/{status}")
    public ResponseEntity<List<CarOrder>> displayOrderByStatus(final @PathVariable Status status) {
       return ResponseEntity.ok(carOrderService.displayCarsByStatus(status));
    }
    @PatchMapping("/updateStatus/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateOrderStatus(final @PathVariable Long orderId, final @RequestParam Status status,
                                                    final Update update
    ) {
    carOrderService.updateOrderStatus(orderId, status, update);
        return ResponseEntity.ok("Order status was successfully updated");
    }
    @PostMapping("/addCarPart/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addCarParts(final @PathVariable Long orderId, final @RequestBody CarPartDTO carPartDTO) {
        carOrderService.addCarPart(orderId, carPartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Car part was added successfully");
    }
    @PatchMapping("/setPrice")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateOrderPrice(final @PathVariable Long orderId, final @RequestParam BigDecimal price) {
        carOrderService.setOrderPrice(orderId, price);
        return ResponseEntity.ok("Price set successfully");
    }
    @GetMapping("/getAllCarOrderBetweenDates")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CarOrder>> getAllCarOrderBetweenDates(final @RequestParam LocalDateTime startDate,
                                                     final @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(carOrderService.getCarOrdersBetweenDates(startDate, endDate));
    }
    @GetMapping("/getTotalPriceByCarParts")
    public ResponseEntity<BigDecimal> getTotalPriceByCarParts(final Long id) {
        return ResponseEntity.ok(carOrderService.getTotalPriceByCarParts(id));
    }
    @GetMapping("/getUnavailableDateTime")
    public ResponseEntity<List<LocalDateTime>> getUnavailableDateTime() {
        return ResponseEntity.ok(carOrderService.getUnavailableDateTime());
    }
    @PatchMapping("/setStartDate/{orderId}")
    public ResponseEntity<String> setOrderStartDate(final @PathVariable Long orderId,
                                                  final @RequestParam LocalDateTime startDate) {
         carOrderService.setStartDate(orderId, startDate);
         return ResponseEntity.ok("Start date set successfully");
    }
    @PatchMapping("/setEndDate/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> setOrderEndDate(final @PathVariable Long orderId,
                                                  final @RequestParam LocalDateTime endDate) {
         carOrderService.setEndDate(orderId, endDate);
         return ResponseEntity.ok("End date set successfully");
    }
    @DeleteMapping("/deleteCarPart/{orderId}/{carPartId}")
    public ResponseEntity<String> deleteCarPart(final @PathVariable Long orderId, final @PathVariable Long carPartId) {
        carOrderService.deleteCarPart(orderId, carPartId);
        return ResponseEntity.ok("Car part deleted successfully");
    }
    @PatchMapping("/updateCarInOrder")
    public ResponseEntity<String> updateCarInOrder(final Long orderId, final CarDTO carDTO) {
        carOrderService.updateCarInOrder(orderId, carDTO);
        return ResponseEntity.ok("Car was updated successfully");
    }
    @PatchMapping("/updateUserInOrder")
    public ResponseEntity<String> updateUserInOrder(final Long orderId, final UserDTO userDTO) {
        carOrderService.updateUserInOrder(orderId,userDTO);
        return ResponseEntity.ok("User was updated in car successfully");
    }
}

