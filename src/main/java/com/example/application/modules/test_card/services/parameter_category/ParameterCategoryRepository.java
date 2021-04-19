package com.example.application.modules.test_card.services.parameter_category;

import com.example.application.modules.test_card.data.test_card_part_parameter_category.ParameterCategoryEntity;
import com.example.application.modules.test_card.data.test_card_part.TestCardPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ParameterCategoryRepository extends JpaRepository<ParameterCategoryEntity, Long> {

    List<TestCardPartEntity> findAllByTestCardPart(TestCardPartEntity testCardPart);
}
