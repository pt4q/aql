package pl.com.pt4q.product_manager.modules.product.services.product_series;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.pt4q.product_manager.modules.product.data.product_part.ProductPartEntity;
import pl.com.pt4q.product_manager.modules.product.data.product_series.ProductSeriesEntity;

@Repository
interface ProductSeriesCrudRepository extends JpaRepository<ProductSeriesEntity, Long> {

}
