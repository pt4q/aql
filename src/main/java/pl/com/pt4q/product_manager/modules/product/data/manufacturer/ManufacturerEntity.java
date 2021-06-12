package pl.com.pt4q.product_manager.modules.product.data.manufacturer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.product.data.manufacturer_address.ManufacturerAddressEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ManufacturerEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private ManufacturerTypeEntity manufacturerType;

    @OneToOne
    private ManufacturerAddressEntity address;

    private String manufacturerName;
    private String description;
}

