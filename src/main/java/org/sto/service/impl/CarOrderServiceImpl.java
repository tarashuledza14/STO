package org.sto.service.impl;

import lombok.RequiredArgsConstructor;
import org.sto.dto.CarDTO;
import org.sto.dto.CarOrderDTO;
import org.sto.dto.CarPartDTO;
import org.sto.dto.UserDTO;
import org.sto.entity.CarOrder;
import org.sto.entity.CarPart;
import org.sto.entity.enumerable.Status;
import org.sto.exception.BadRequestException;
import org.sto.mapper.CarMapper;
import org.sto.mapper.CarOrderMapper;
import org.sto.mapper.CarPartMapper;
import org.sto.mapper.UserMapper;
import org.sto.repository.CarOrderRepository;
import org.sto.service.CarOrderService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CarOrderServiceImpl implements CarOrderService {
    private final CarOrderRepository carOrderRepository;
    private final TelegramBot telegramBot;
    @Override
    public void addCarOrder(final CarOrderDTO carOrderDTO) {
        CarOrder carOrder = CarOrderMapper.fromDTOToEntity(carOrderDTO);
        carOrderRepository.save(carOrder);
    }

    @Override
    public void delete(final Long id) {
        carOrderRepository.deleteById(id);
    }

    @Override
    public List<CarOrder> getAllOrders() {
        List<CarOrder> carOrders = carOrderRepository.findAll().stream().sorted(Comparator.comparing(CarOrder::getStatus))
                .toList();
        if (carOrders.isEmpty()) {
            throw new BadRequestException("Order list is empty");
        }
        return carOrders;
    }

    @Override
    public CarOrder findById(final Long id) {
        return carOrderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Order with this id does not exist"));

    }

    @Override
    public void updateCarInOrder(final Long orderId,final CarDTO carDTO) {
        final CarOrder carOrder = findById(orderId);
        carOrder.setCar(CarMapper.fromDTOToEntity(carDTO));
        carOrderRepository.save(carOrder);
    }

    @Override
    public void updateUserInOrder(final Long orderId, final UserDTO userDTO) {
        final CarOrder carOrder = findById(orderId);
        carOrder.setUser(UserMapper.fromUserDTOToEntity(userDTO));
        carOrderRepository.save(carOrder);
    }

    public List<CarOrder> displayCarsByStatus(final Status status) {
        List<CarOrder> carOrders = carOrderRepository.findAllByStatus(status)
                .stream()
                .toList();
        if (carOrders.isEmpty()) {
            throw new BadRequestException("No car orders found with status: " + status);
        }
        return carOrders;
    }

    @Override
    public void updateOrderStatus(final Long orderId, final Status status, final Update update
    ) {
        System.out.println(update);
        final CarOrder savedCarOrder = findById(orderId);
            savedCarOrder.setStatus(status);
        telegramBot.handleContact(update.getMessage());
        savedCarOrder.setStatus(status);
        carOrderRepository.save(savedCarOrder);
    }

    @Override
    public void addCarPart(final Long orderId, final CarPartDTO carPartDTO) {
        final CarOrder savedCarOrder = findById(orderId);
        CarPart carPart = CarPartMapper.fromDTOToEntity(carPartDTO);
        savedCarOrder.getCarParts().add(carPart);
        carOrderRepository.save(savedCarOrder);
    }

    @Override
    public void setOrderPrice(final Long orderId, final BigDecimal price) {
        final CarOrder carOrder = findById(orderId);
        carOrder.setPrice(price);
    }

    @Override
    public List<CarOrder> getCarOrdersBetweenDates(final LocalDateTime startDate, final  LocalDateTime endDate) {
        return carOrderRepository.findAllByEndDateBetween(startDate, endDate);
    }

    @Override
    public BigDecimal getTotalPriceByCarParts(final Long id) {
        return findById(id).getCarParts().stream().map(CarPart::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<LocalDateTime> getUnavailableDateTime() {
        return null;
    }

    @Override
    public void setStartDate(final Long id, final LocalDateTime startDate) {
        final CarOrder carOrder = findById(id);
        carOrder.setStartDate(startDate);
    }

    @Override
    public void setEndDate(final Long id,final  LocalDateTime endDate) {
        final CarOrder carOrder = findById(id);
        carOrder.setEndDate(endDate);
    }

    @Override
    public void deleteCarPart(final Long carOrderId,final Long carPartId) {
        CarOrder carOrder = carOrderRepository.findById(carOrderId)
                .orElseThrow(() -> new BadRequestException("CarOrder not found with id: " + carOrderId));
        CarPart carPartToRemove = carOrder.getCarParts().stream()
                .filter(carPart -> carPart.getId().equals(carPartId))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("CarPart not found with id: " + carPartId));
        carOrder.getCarParts().remove(carPartToRemove);
        carOrderRepository.save(carOrder);
    }

}
