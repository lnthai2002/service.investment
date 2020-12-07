package info.noip.darkportal.service.investment.api.v1.controller;

import info.noip.darkportal.service.investment.api.v1.dto.InvestmentRequestDTO;
import info.noip.darkportal.service.investment.api.v1.dto.InvestmentResponseDTO;
import info.noip.darkportal.service.investment.domain.Investment;
import info.noip.darkportal.service.investment.service.InvestmentConverter;
import info.noip.darkportal.service.investment.service.InvestmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.UUID;

//TODO: create controller advice to handle exception
@RestController
@RequestMapping("/v1/investments")
public class InvestmentController {

    private InvestmentService investmentService;
    private InvestmentConverter investmentConverter;

    public InvestmentController(InvestmentService investmentService, InvestmentConverter investmentConverter) {
        this.investmentService = investmentService;
        this.investmentConverter = investmentConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentResponseDTO> loadInvestment(@PathVariable UUID id) {
        Investment investment = investmentService.get(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(investmentConverter.fromDomain(investment));
    }

    /**
     * This is to simulate an investment, not storing it
     * */
    @GetMapping("/try")
    public ResponseEntity<InvestmentResponseDTO> tryInvestment(@RequestParam Long principalCents,
                                                               @RequestParam Long monthlyDepCents,
                                                               @RequestParam BigDecimal rate,
                                                               @RequestParam Integer months) {
        Investment investment = Investment.builder()
                .principalCents(principalCents)
                .monthlyDepCents(monthlyDepCents)
                .rate(rate)
                .months(months)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(investmentConverter.fromDomain(investment));
    }

    //TODO: the investment must have been calculated before, we should not recalculate it.
    // Possible solution is using JWT to sign the result of the trial and validate it here and save without recalculating
    @PostMapping({"", "/"})
    public ResponseEntity<InvestmentResponseDTO> createInvestment(@RequestBody InvestmentRequestDTO request,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        Investment investment = investmentConverter.fromDTO(request);
        investmentService.save(investment);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION,
                uriComponentsBuilder
                        .path("/v1/investments/{investmentId}")
                        .buildAndExpand(investment.getInvestmentId())
                        .toUriString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .body(null);
    }
}
