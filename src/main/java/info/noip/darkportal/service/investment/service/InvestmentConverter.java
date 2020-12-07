package info.noip.darkportal.service.investment.service;

import info.noip.darkportal.service.investment.api.v1.dto.InvestmentResponseDTO;
import info.noip.darkportal.service.investment.domain.Investment;
import org.springframework.stereotype.Component;

@Component
public class InvestmentConverter {
    public InvestmentResponseDTO fromDomain(Investment model) {
        InvestmentResponseDTO dto = new InvestmentResponseDTO();
        dto.setInvestmentId(model.getInvestmentId().toString());
        dto.setMonths(model.getMonths());
        dto.setMonthlyDepCents(model.getMonthlyDepCents());
        dto.setRate(model.getRate());
        dto.setPrincipalCents(model.getPrincipalCents());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        return dto;
    }
}
