package com.example.application.data.service.test_card_associated.parameter_category;

import com.example.application.data.entity.test_card_associated.parameter_category.ParameterCategoryEntity;
import com.example.application.data.entity.test_card_associated.test_card.TestCardEntity;
import com.example.application.data.service.test_card_associated.parameter_category.exceptions.ParameterCategoryNotFoundException;
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

    public List<ParameterCategoryEntity> findAllParameterCategoryByTestCardTemplate(TestCardEntity testCard){
       return parameterCategoryRepository.findAllByTestCard(testCard);
    }
}
