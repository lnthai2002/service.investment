package info.noip.darkportal.service.investment;

import info.noip.darkportal.service.investment.domain.Investment;
import info.noip.darkportal.service.investment.service.InvestmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Profile("postman-test")
@Component
public class Bootstrap implements CommandLineRunner {

    private InvestmentService investmentService;

    public Bootstrap(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Investment i1 = Investment.builder()
                .principalCents(10000000L)
                .monthlyDepCents(10000L)
                .rate(new BigDecimal("0.05"))
                .months(240)
                .build();
        investmentService.save(i1);
        System.out.println(i1.getInvestmentId());

        Investment i2 = Investment.builder()
                .principalCents(20000000L)
                .monthlyDepCents(20000L)
                .rate(new BigDecimal("0.08"))
                .months(240)
                .build();
        investmentService.save(i2);
    }
}
