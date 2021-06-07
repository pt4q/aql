package pl.com.pt4q.product_manager.modules.product.services.unit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.pt4q.product_manager.modules.product.data.unit.UnitEntity;
import pl.com.pt4q.product_manager.modules.product.services.unit.exceptions.UnitAlreadyExistsException;
import pl.com.pt4q.product_manager.modules.product.services.unit.exceptions.UnitNotFoundException;
import pl.com.pt4q.product_manager.service_utils.CustomCrudServiceInterface;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UnitCrudService implements CustomCrudServiceInterface<UnitEntity, Long, UnitNotFoundException, UnitAlreadyExistsException> {

    @Autowired
    private UnitCrudRepository unitCrudRepository;

    @Override
    public UnitEntity create(UnitEntity unit) throws UnitAlreadyExistsException {
        try {
            getByIdOrThrow(unit.getId());
        } catch (UnitNotFoundException e) {
            unit = unitCrudRepository.save(unit);
            log.info(String.format("Unit '%s %s' has been created on id: %d", unit.getName(), unit.getUnits(), unit.getId()));
            return unit;
        }
        throw new UnitAlreadyExistsException(String.format("Unit '%s %s' already exists on id: %d", unit.getName(), unit.getUnits(), unit.getId()));
    }

    @Override
    public UnitEntity getByIdOrThrow(Long id) throws UnitNotFoundException {
        if (id != null) {
            Optional<UnitEntity> unit = unitCrudRepository.findById(id);
            if (unit.isPresent())
                return unit.get();
            else
                throw new UnitNotFoundException(String.format("Unit with id: %d not exists", id));
        }
        throw new UnitNotFoundException(String.format("Unit id: %d", id));
    }

    @Override
    public UnitEntity updateOrThrow(UnitEntity unit) throws UnitNotFoundException {
        getByIdOrThrow(unit.getId());
        unit = unitCrudRepository.save(unit);
        log.info(String.format("Unit '%s %s' has been updated on id: %d", unit.getName(), unit.getUnits(), unit.getId()));
        return unit;
    }

    @Override
    public List<UnitEntity> getAll() {
        return unitCrudRepository.findAll();
    }

}
