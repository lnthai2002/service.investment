package info.noip.darkportal.service.investment.api.v1.controller;

import info.noip.darkportal.service.investment.domain.Investment;
import info.noip.darkportal.service.investment.service.InvestmentConverter;
import info.noip.darkportal.service.investment.service.InvestmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void should_be_able_to_get_an_existing_investment_json_with_status_OK() throws Exception {
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

    @Test
    void should_be_able_to_return_an_investment_without_saving_it() throws Exception {
        //given some investment info
        Long principal = 20000L;
        Long monthlyDep = 1000L;
        BigDecimal rate = new BigDecimal("0.07");
        Integer months = 60;
        //act and validate that we received a location header and that there is no call to investment service
        mvc.perform(get("/v1/investments/try?"
                + "&principalCents=" + principal
                + "&monthlyDepCents=" + monthlyDep
                + "&rate=" + new BigDecimal("0.07")
                + "&months=" + 60)
                )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.principalCents").value(principal))
                .andExpect(jsonPath("$.monthlyDepCents").value(monthlyDep))
                .andExpect(jsonPath("$.rate").value(rate))
                .andExpect(jsonPath("$.months").value(months));
        verify(investmentService, times(0)).save(any());
    }

    @Test
    void should_create_a_new_investment_and_return_its_location() throws Exception {
        //given a calculated investment
        Investment investment = Investment.builder()
                .principalCents(2000L)
                .monthlyDepCents(100L)
                .rate(new BigDecimal("0.05"))
                .months(65)
                .build();
        //act by calling the API
        mvc.perform(post("/v1/investments")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(investment)))
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(status().isCreated());
        verify(investmentService, times(1)).save(any());
    }
}