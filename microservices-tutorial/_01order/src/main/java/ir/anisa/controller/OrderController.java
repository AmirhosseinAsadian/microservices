package ir.anisa.controller;

import ir.anisa.dto.OrderDTO;
import ir.anisa.sarvice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public void saveOrder(String productName) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setName("1");
        orderService.saveOrder(orderDTO, productName);
    }
}
