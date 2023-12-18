package org.sto.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.sto.entity.CarPart;
import org.sto.repository.CarPartRepository;
import org.sto.service.CarPartsService;

@Service
@RequiredArgsConstructor
public class CarPartsServiceImpl implements CarPartsService {
    private final CarPartRepository carPartRepository;
    @Override
    public CarPart findById(final Long id) {
        return carPartRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(final CarPart carPart) {
        carPartRepository.save(carPart);
    }

    @Override
    public void delete(final Long id) {
        carPartRepository.deleteById(id);
    }

    @Override
    public void update(final Long id, final @Valid CarPart carPart) {
        CarPart savedCarPart = findById(id);
        savedCarPart.setId(carPart.getId());
        savedCarPart.setCode(carPart.getCode());
        savedCarPart.setPrice(carPart.getPrice());
        savedCarPart.setTitle(carPart.getTitle());
    }
}
