package ir.anisa;

import ir.anisa.entity.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testOrderGettersAndSetters() {
        Order order = new Order();

        order.setId(1L);
        order.setName("Test Order");
        order.setProductName("Test Product Name");

        assertEquals(1L, order.getId());
        assertEquals("Test Order", order.getName());
        assertEquals("Test Product Name", order.getProductName());
    }

    @Test
    public void testOrderNoArgsConstructor() {
        Order order = new Order();
        assertNotNull(order);
    }
}
