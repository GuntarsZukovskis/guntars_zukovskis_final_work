package lv.lu.finalwork.service;

import lv.lu.finalwork.models.repository.Product;
import lv.lu.finalwork.models.ui.ProductInputData;
import lv.lu.finalwork.repository.ProductRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldStoreProduct() {
        final ProductInputData inputData = new ProductInputData();
        final Product product = new Product();

        given(mapper.mapFrom(inputData)).willReturn(product);
        service.save(inputData);
        verify(repository).save(product);
        verify(mapper).mapFrom(inputData);

        verifyNoMoreInteractions(mapper, repository);
    }

    @Test
    public void shouldThrowErrorWhenFindByNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Product ID cannot be null");
        service.findById(null);
    }
}