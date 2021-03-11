package com.example.application.data.entity.product;

import com.example.application.data.entity.test_card.TestCardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductEntity {

    @Id
    private Long id;
    private String productNumber;

    @OneToMany
    private List<TestCardEntity> testCards;
}
