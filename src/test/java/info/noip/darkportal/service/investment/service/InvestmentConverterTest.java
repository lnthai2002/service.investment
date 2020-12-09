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
        final Long principalCents = 1000L;
        final long monthlyDepCents = 200L;
        final BigDecimal rate = new BigDecimal("0.07");
        final int months = 120;
        final UUID investmentId = UUID.randomUUID();
        Investment investment = Investment.builder()
                .principalCents(principalCents)
                .monthlyDepCents(monthlyDepCents)
                .rate(rate)
                .months(months)
                .investmentId(investmentId)
                .build();
        //act
        InvestmentResponseDTO dto = investmentConverter.fromDomain(investment);
        //validate
        assertNotNull(dto);
        assertEquals(principalCents, dto.getPrincipalCents());
        assertEquals(monthlyDepCents, dto.getMonthlyDepCents());
        assertEquals(rate, dto.getRate());
        assertEquals(months, dto.getMonths());
        assertEquals(investmentId.toString(), dto.getInvestmentId());
    }
}