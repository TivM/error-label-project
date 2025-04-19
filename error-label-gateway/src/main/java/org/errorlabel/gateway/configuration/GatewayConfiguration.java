package org.errorlabel.gateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class GatewayConfiguration {

    @Value("${application.gateway.auth}")
    String auth;

    @Value("${application.gateway.banking}")
    String core;

    @Value("${application.gateway.stock}")
    String stock;

    @Value("${application.gateway.accounts}")
    String accounts;

    @Value("${application.gateway.files}")
    String files;

    @Bean
    RouteLocator gatewayRoute(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes()
                .route("auth", route -> route
                        .path("/api/auth/**")
                        .uri(auth))
                .route("banking", route -> route
                        .path("/projects/**")
                        .uri(core))
                .build();
    }
}
