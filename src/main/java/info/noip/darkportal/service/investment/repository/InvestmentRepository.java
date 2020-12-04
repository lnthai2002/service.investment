package info.noip.darkportal.service.investment.repository;

import info.noip.darkportal.service.investment.domain.Investment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvestmentRepository extends PagingAndSortingRepository<Investment, UUID> {
    Investment save(Investment i);
}
