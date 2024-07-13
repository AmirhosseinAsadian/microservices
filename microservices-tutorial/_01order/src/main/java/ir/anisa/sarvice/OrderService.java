package ir.anisa.sarvice;

import ir.anisa.dto.CouponDTO;
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
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    private HibernateOrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private ProductDTO getProduct(String productName) {

        ProductDTO productDTO = restTemplate.getForObject("http://localhost:8081/api/v1/product/getProduct?productName=p1", ProductDTO.class, productName);
        return productDTO;
    }

    private CouponDTO getCoupon(String couponCode) {
        CouponDTO couponDTO = restTemplate.getForObject("http://localhost:8081//api/v1/coupon/{code}", CouponDTO.class, couponCode);
        return couponDTO;
    }

    public void saveOrder(OrderDTO orderDTO, String productName) {
        ProductDTO product = getProduct(productName);
        CouponDTO coupon = getCoupon(productName);
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setProductName(product.getName());
        order.setDiscount(prepareProductDiscount(product, coupon));
        orderRepository.saveOrder(order);
    }

    private BigDecimal prepareProductDiscount(ProductDTO product, CouponDTO coupon) {
        BigDecimal discount = product.getPrice().multiply(coupon.getPercent()).divide(new BigDecimal("100"));
        return discount;
    }
}
