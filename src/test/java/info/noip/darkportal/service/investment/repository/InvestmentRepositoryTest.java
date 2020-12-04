package info.noip.darkportal.service.investment.repository;

import info.noip.darkportal.service.investment.domain.Investment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InvestmentRepositoryTest {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Test
    void should_save_successfully() {
        //given a new investment
        Investment investment = new Investment()
                .principalCents(1000000L)
                .monthlyDepCents(10000L)
                .rate(new BigDecimal("0.07"))
                .months(12);
        //act
        investmentRepository.save(investment);
        //validate
        assertNotNull(investmentRepository.findById(investment.investmentId())
                .orElseThrow(() -> new EntityNotFoundException()));
    }
}