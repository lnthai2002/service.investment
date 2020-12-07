package info.noip.darkportal.service.investment.api.v1.controller;

import info.noip.darkportal.service.investment.domain.Investment;
import info.noip.darkportal.service.investment.service.InvestmentConverter;
import info.noip.darkportal.service.investment.service.InvestmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class InvestmentControllerTest extends AbstractControllerTest {

    @Mock
    private InvestmentService investmentService;

    private InvestmentController investmentController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this.getClass());//inject mock objects to this class
        investmentController = new InvestmentController(investmentService, new InvestmentConverter());
        mvc = MockMvcBuilders.standaloneSetup(investmentController)
                .build();
    }
    @Test
    void should_be_able_to_get_an_investment_json_with_status_OK() throws Exception {
        UUID id = UUID.randomUUID();
        //given an existing investment in the Db
        Investment investment = Investment.builder()
                .principalCents(1000L)
                .monthlyDepCents(0L)
                .rate(BigDecimal.valueOf(0.07))
                .months(120)
                .investmentId(id)
                .build();
        //and the investment service can return it correctly
        when(investmentService.get(investment.getInvestmentId())).thenReturn(investment);
        //act
        mvc.perform(get("/v1/investments/" + id.toString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.investmentId").value(id.toString()));
    }
}