package ir.digixo;

import ir.digixo.entity.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductGettersAndSetters() {
        Product product = new Product();

        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(new BigDecimal("19.99"));

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("Test Description", product.getDescription());
        assertEquals(new BigDecimal("19.99"), product.getPrice());
    }

    @Test
    public void testProductNoArgsConstructor() {
        Product product = new Product();
        assertNotNull(product);
    }
}
