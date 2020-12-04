package info.noip.darkportal.service.investment.service.jpa;

import info.noip.darkportal.service.investment.domain.Investment;
import info.noip.darkportal.service.investment.repository.InvestmentRepository;
import info.noip.darkportal.service.investment.service.InvestmentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Profile("jpa")
@Service
public class JpaInvestmentService implements InvestmentService {
    private InvestmentRepository investmentRepository;

    public JpaInvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    @Override
    public Investment get(UUID id) {
        return investmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }

    @Override
    public Investment recalculate(Investment investment) {
        return null;
    }
}
