package ir.anisa.sarvice;

import ir.anisa.discount.client.DiscountClient;
import ir.anisa.discount.dto.Coupon;
import ir.anisa.dto.OrderDTO;
import ir.anisa.dto.ProductDTO;
import ir.anisa.entity.Order;
import ir.anisa.repository.HibernateOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private HibernateOrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscountClient discountClient;

    @Autowired
    private WebClient webClient;

    private ProductDTO getProduct(String productName) {
//        ProductDTO productDTO = restTemplate.getForObject("http://PRODUCT/api/v1/product/getProduct/{productName}", ProductDTO.class, productName);
        ProductDTO productDTO = webClient.get().uri("http://localhost:8081/api/v1/product/getProduct/{productName}", productName).retrieve().bodyToMono(ProductDTO.class).block();
//        ProductDTO productDTO = RestClient.builder().build().get().uri("http://PRODUCT/api/v1/product/getProduct/{productName}", productName).retrieve().body(ProductDTO.class);

        return productDTO;
    }

    private Coupon getCoupon(String productName) {
        Coupon coupon = discountClient.findByProductName(productName);
        return coupon;
    }

    public void saveOrder(OrderDTO orderDTO, String productName) {
        ProductDTO product = getProduct(productName);
        Coupon coupon = getCoupon(productName);
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setProductName(product.getName());
        order.setDiscount(prepareProductDiscount(product, coupon));
        orderRepository.saveOrder(order);
    }

    private BigDecimal prepareProductDiscount(ProductDTO product, Coupon coupon) {
        BigDecimal discount = product.getPrice().multiply(coupon.getPercent()).divide(new BigDecimal("100"));
        return discount;
    }
}
