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

    @Bean
    RouteLocator gatewayRoute(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes()
                .route("auth", route -> route
                        .path("/api/auth/**")
                        .uri(auth))
                .build();
    }
}
