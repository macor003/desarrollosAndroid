package com.grid.ventas.cr.refundrestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableJpaRepositories(basePackages =
// "com.grid.ventas.cr.refundrestserver.repository")
// @EntityScan(basePackages = {"crjpa400",
// "com.grid.ventas.cr.refundrestserver.entity"})
// @EnableCaching
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
