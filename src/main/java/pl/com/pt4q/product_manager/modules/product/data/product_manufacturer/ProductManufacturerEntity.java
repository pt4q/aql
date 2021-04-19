package pl.com.pt4q.product_manager.modules.product.data.product_manufacturer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductManufacturerEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String ManufacturerName;
    private String country;
    private String description;
}
