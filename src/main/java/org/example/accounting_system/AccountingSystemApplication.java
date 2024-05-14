package org.example.accounting_system;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AccountingSystemApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountingSystemApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
