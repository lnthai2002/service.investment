package info.noip.darkportal.service.investment.service;

import info.noip.darkportal.service.investment.api.v1.dto.InvestmentResponseDTO;
import info.noip.darkportal.service.investment.domain.Investment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InvestmentConverterTest {

    private InvestmentConverter investmentConverter;
    @BeforeEach
    void setUp() {
        investmentConverter = new InvestmentConverter();
    }

    @Test
    void should_be_able_to_convert_investment_to_response_dto() {
        //given a domain object
        Investment investment = new Investment()
                .principalCents(1000L)
                .monthlyDepCents(200L)
                .rate(new BigDecimal("0.07"))
                .months(120)
                .investmentId(UUID.randomUUID());
        //act
        InvestmentResponseDTO dto = investmentConverter.fromDomain(investment);
        //validate
        assertNotNull(dto.getInvestmentId());
    }
}