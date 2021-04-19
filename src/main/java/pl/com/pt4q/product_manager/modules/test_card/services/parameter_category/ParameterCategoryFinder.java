package pl.com.pt4q.product_manager.modules.test_card.services.parameter_category;

import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part_parameter_category.ParameterCategoryEntity;
import pl.com.pt4q.product_manager.modules.test_card.data.test_card_part.TestCardPartEntity;
import pl.com.pt4q.product_manager.modules.test_card.services.parameter_category.exceptions.ParameterCategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterCategoryFinder {

    @Autowired
    private ParameterCategoryRepository parameterCategoryRepository;

    public ParameterCategoryEntity findParameterCategoryById(Long id) throws ParameterCategoryNotFoundException {
        if (id != null){
            Optional<ParameterCategoryEntity> parameterCategoryTemplate = parameterCategoryRepository.findById(id);
            if (parameterCategoryTemplate.isPresent())
                ;
            else
                throw new ParameterCategoryNotFoundException(String.format("Parameter category with id:%d not exists", id));
        }
        throw new ParameterCategoryNotFoundException(String.format("Parameter category with id:%d", id));
    }

    public List<TestCardPartEntity> findAllParameterCategoryByTestCardTemplate(TestCardPartEntity testCardPart){
       return parameterCategoryRepository.findAllByTestCardPart(testCardPart);
    }
}
