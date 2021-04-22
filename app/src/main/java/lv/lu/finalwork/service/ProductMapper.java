package lv.lu.finalwork.service;

import lv.lu.finalwork.models.repository.Product;
import lv.lu.finalwork.models.repository.ProductCategory;
import lv.lu.finalwork.models.ui.ProductData;
import lv.lu.finalwork.models.ui.ProductInputData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {

    public ProductData mapFrom(Product product) {
        return new ProductData(
                product.getId().toString(),
                product.getName(),
                product.getPrice().toPlainString(),
                product.getCategory().name());
    }

    public Product mapFrom(ProductInputData productInputData) {
        Product product = new Product();
        product.setName(productInputData.getName());
        product.setPrice(BigDecimal.valueOf(productInputData.getPrice()));
        product.setCategory(ProductCategory.valueOf(productInputData.getCategory()));
        if (productInputData.getDiscount() != null) {
            product.setDiscount(BigDecimal.valueOf(productInputData.getDiscount()));
        }
        if (productInputData.getDescription() != null) {
            product.setDescription(productInputData.getDescription());
        }
        return product;
    }

}
