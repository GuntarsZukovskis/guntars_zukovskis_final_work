package lv.lu.finalwork.service;

import lv.lu.finalwork.models.repository.Product;
import lv.lu.finalwork.models.ui.ProductInputData;
import lv.lu.finalwork.repository.ProductRepository;
import lv.lu.finalwork.validation.ProductValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductMapper mapper;
    @Mock
    private ProductValidator validator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldStoreProduct() {
        final ProductInputData inputData = new ProductInputData();
        final Product product = new Product();

        given(mapper.mapFrom(inputData)).willReturn(product);
        service.save(inputData);

        InOrder inOrder = Mockito.inOrder(mapper, repository, validator);
        inOrder.verify(validator).validate(inputData);
        inOrder.verify(mapper).mapFrom(inputData);
        inOrder.verify(repository).save(product);

        verifyNoMoreInteractions(mapper, repository, validator);
    }

    @Test
    public void shouldThrowErrorWhenFindByNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Product ID cannot be null");
        service.findById(null);
    }
}