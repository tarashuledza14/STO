package org.sto.service;

import org.sto.dto.CarPartDTO;
import org.sto.entity.CarPart;

public interface CarPartsService {
    CarPart findById(final Long id);
    void save(final CarPart carPart);
    void delete(final Long id);
    void update(final Long id, final CarPart carPart);
}
