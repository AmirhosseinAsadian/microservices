package ir.anisa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class APIGateway {
    public static void main(String[] args) {
        SpringApplication.run(APIGateway.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        predicateSpec ->
                                predicateSpec.path("/api/v1/product/**").uri("lb://PRODUCT")
                )
                .route(
                        predicateSpec ->
                                predicateSpec.path("/api/v1/coupon/**").uri("lb://DISCOUNT")
                )
                .route(
                        predicateSpec ->
                                predicateSpec.path("/api/v1/order/**").uri("lb://ORDER")
                )
                .build();

    }
}
