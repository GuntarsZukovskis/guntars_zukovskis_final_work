package lv.lu.finalwork.repository;

import lv.lu.finalwork.models.Product;

import java.util.*;

public class ProductRepository implements Repository<Product> {

    private final Map<Long, Product> repository;
    private Long idCounter = 0L;

    public ProductRepository() {
        this.repository = new HashMap<>();
    }

    public ProductRepository(Map<Long, Product> repository) {
        this.repository = repository;
    }

    @Override
    public Long save(Product productEntity) {
        productEntity.setId(idCounter);
        repository.put(idCounter, productEntity);
        return idCounter++;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public void delete(Long id) {
        repository.remove(id);
    }
}
