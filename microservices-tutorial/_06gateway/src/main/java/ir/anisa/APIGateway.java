package ir.anisa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

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
                                predicateSpec.path("/api/v1/product/**")
                                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                                .rewritePath("/api/v1/product/(?<remaining>.*)", "/${remaining}")
                                                .addRequestHeader("my_response_header", LocalDateTime.now().toString())
                                                .circuitBreaker(config -> config
                                                        .setName("apigatewayCircuitBreaker")
                                                        .setFallbackUri("forward:/circuitBreaker/fallback")
                                                )
                                        )
                                        .uri("lb://PRODUCT")

                )
                .route(
                        predicateSpec ->
                                predicateSpec.path("/api/v1/coupon/**")
                                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                                .rewritePath("/api/v1/coupon/(?<remaining>.*)", "/${remaining}")
                                                .retry(retryConfig ->
                                                        retryConfig.setRetries(3)
                                                                .setMethods(HttpMethod.GET)
                                                                .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                                                )
                                        )
                                        .uri("lb://DISCOUNT")
                )
                .route(
                        predicateSpec ->
                                predicateSpec.path("/api/v1/order/**").uri("lb://ORDER")
                )
                .build();

    }
}
