package ir.ahs441.wallet.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = "ir.ahs441.wallet.dataaccess")
@EnableJpaRepositories(basePackages = "ir.ahs441.wallet.dataaccess")
@SpringBootApplication(scanBasePackages = "ir.ahs441")
@EnableScheduling
public class WalletServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }
}
