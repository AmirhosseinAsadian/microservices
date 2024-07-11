package ir.anisa.sarvice;

import ir.anisa.dto.OrderDTO;
import ir.anisa.dto.ProductDTO;
import ir.anisa.entity.Order;
import ir.anisa.repository.HibernateOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderService {

    @Autowired
    private HibernateOrderRepository orderRepository;

    private ProductDTO getProduct(String productName) {
        RestClient restClient = RestClient.create();
        ResponseEntity<ProductDTO> responseEntity = restClient.get()
                .uri("http://localhost:8081/api/v1/product/getProduct?productName=p1")
                .retrieve()
                .toEntity(ProductDTO.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        ProductDTO productDTO = responseEntity.getBody();
        return productDTO;
    }

    public void saveOrder(OrderDTO orderDTO, String productName) {
        ProductDTO product = getProduct(productName);
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setProductName(product.getName());
        orderRepository.saveOrder(order);
    }
}
