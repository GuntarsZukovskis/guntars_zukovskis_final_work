package lv.lu.finalwork.validation;

import lv.lu.finalwork.models.ProductValidationException;
import lv.lu.finalwork.models.repository.ProductCategory;
import lv.lu.finalwork.models.ui.ProductInputData;
import org.springframework.util.StringUtils;

public class ProductValidator {

    public void validate(ProductInputData productInputData) {
        if (!StringUtils.hasLength(productInputData.getName())) {
            throw new ProductValidationException("Field 'name' should not be empty");
        }
        if (productInputData.getPrice() < 0) {
            throw new ProductValidationException("Field 'price' should not be negative");
        }

        try {
            ProductCategory.valueOf(productInputData.getCategory());
        } catch (IllegalArgumentException ex) {
            throw new ProductValidationException("Field 'category' input is unrecognized");
        }

        if (productInputData.getDiscount() < 0) {
            throw new ProductValidationException("Field 'discount' should not be negative");
        }
    }
}
