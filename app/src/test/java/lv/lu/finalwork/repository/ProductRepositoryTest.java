package lv.lu.finalwork.repository;

import lv.lu.finalwork.models.repository.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductRepositoryTest {
    
    private Map<Long, Product> repositoryMock;
    private ProductRepository victim;

    @Before
    public void setUp() throws Exception {
        repositoryMock = new HashMap<>();
        victim = new ProductRepository(repositoryMock);
    }

    @Test
    public void shouldFindAllProducts() {
        Product orange = new Product();
        Product fish = new Product();
        repositoryMock.put(1L, orange);
        repositoryMock.put(2L, fish);

        List<Product> result = victim.findAll();
        assertNotNull(result);
        assertTrue(result.contains(orange));
        assertTrue(result.contains(fish));
    }

    @Test
    public void shouldFindProductById() {
        Product orange = new Product();
        repositoryMock.put(2L, orange);

        Optional<Product> result = victim.findById(2L);
        assertTrue(result.isPresent());
        assertSame(orange, result.get());
    }

    @Test
    public void shouldNotFailWhenProductNotFound() {
        Optional<Product> result = victim.findById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    public void shouldDeleteProductById() {
        repositoryMock.put(1L, new Product());
        victim.delete(1L);
        assertTrue(repositoryMock.isEmpty());
    }

    @Test
    public void shouldSaveProduct() {
        Product orange = new Product();
        Long product = victim.save(orange);
        assertEquals(product, orange.getId());
        assertNotNull(product);
        assertTrue(repositoryMock.containsKey(product));
        assertEquals(orange, repositoryMock.get(product));
    }
}