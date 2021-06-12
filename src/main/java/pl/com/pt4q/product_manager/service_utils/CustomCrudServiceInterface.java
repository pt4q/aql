package pl.com.pt4q.product_manager.service_utils;

import pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions.ProductCategoryAlreadyExistsException;

import java.util.List;

public interface CustomCrudServiceInterface<E, ID, NOT_FOUND extends Exception, ALREADY_EXISTS extends Exception> {

    E create(E entity) throws ALREADY_EXISTS, ProductCategoryAlreadyExistsException;

    E getByIdOrThrow(ID id) throws NOT_FOUND;

    E updateOrThrow(E entity) throws NOT_FOUND;

    List<E> getAll();
}
