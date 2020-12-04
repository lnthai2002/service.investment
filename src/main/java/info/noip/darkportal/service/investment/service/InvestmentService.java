package info.noip.darkportal.service.investment.service;

import info.noip.darkportal.service.investment.domain.Investment;

import java.util.UUID;

public interface InvestmentService {
    Investment get(UUID id);
    Investment save(Investment investment);
    Investment recalculate(Investment investment);
}
