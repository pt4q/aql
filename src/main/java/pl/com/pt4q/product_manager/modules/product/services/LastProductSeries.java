package pl.com.pt4q.product_manager.modules.product.services;

import pl.com.pt4q.product_manager.modules.product.data.product_series.ProductSeriesEntity;

import java.util.OptionalInt;
import java.util.Set;


public class LastProductSeries {

    private Set<ProductSeriesEntity> seriesSet;

    public LastProductSeries(Set<ProductSeriesEntity> seriesSet) {
        this.seriesSet = seriesSet;
    }

    public OptionalInt getLatest(){
        OptionalInt seriesValue = seriesSet
                .stream()
                .mapToInt(series -> Integer.parseInt(series.getSeries()))
                .max();
        return seriesValue;
    }
}
