package info.noip.darkportal.service.investment.service;

import info.noip.darkportal.service.investment.api.v1.dto.InvestmentRequestDTO;
import info.noip.darkportal.service.investment.api.v1.dto.InvestmentResponseDTO;
import info.noip.darkportal.service.investment.domain.Investment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvestmentConverter {
    public InvestmentResponseDTO fromDomain(Investment model) {
        InvestmentResponseDTO dto = new InvestmentResponseDTO();
        dto.setMonths(model.getMonths());
        dto.setMonthlyDepCents(model.getMonthlyDepCents());
        dto.setRate(model.getRate());
        dto.setPrincipalCents(model.getPrincipalCents());
        dto.setFutureValueCents(model.getFutureValueCents());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        dto.setInvestmentId(
                Optional.ofNullable(model.getInvestmentId())
                        .map(u -> u.toString())
                        .orElse(null));
        return dto;
    }

    public Investment fromDTO(InvestmentRequestDTO request) {
        Investment investment = Investment.builder()
                .principalCents(request.getPrincipalCents())
                .monthlyDepCents(request.getMonthlyDepCents())
                .rate(request.getRate())
                .months(request.getMonths())
                .futureValueCents(request.getFutureValueCents())
                .build();
        return investment;
    }
}
