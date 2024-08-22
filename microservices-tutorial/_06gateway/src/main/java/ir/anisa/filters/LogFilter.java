package ir.anisa.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
@Slf4j
public class LogFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        //req time
        // pre processing


        long start = System.currentTimeMillis();
        chain.filter(exchange).then(Mono.fromRunnable(
                () -> {
                    //post processing
                    long end = System.currentTimeMillis();
                    log.info("time: " + (end - start));
                }
        ));
        return null;
    }
}
