package lv.lu.finalwork.service;

import lv.lu.finalwork.models.ItemNotFoundException;
import lv.lu.finalwork.models.repository.Product;
import lv.lu.finalwork.models.ui.ProductData;
import lv.lu.finalwork.models.ui.ProductInputData;
import lv.lu.finalwork.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Autowired
    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(ProductInputData productInputData) {
        repository.save(mapper.mapFrom(productInputData));
    }

    public List<ProductData> findAll() {
        return repository.findAll().stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    public Product findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        Optional<Product> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ItemNotFoundException(
                    String.format("Product by ID: %d is not found", id));
        }
        return result.get();
    }


    public void delete(Long id) {

    }

}
