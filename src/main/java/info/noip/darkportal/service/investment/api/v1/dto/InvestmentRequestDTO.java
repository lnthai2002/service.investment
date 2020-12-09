package info.noip.darkportal.service.investment.api.v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class InvestmentRequestDTO implements Serializable {
    private Long principalCents;
    private Long monthlyDepCents;
    private BigDecimal rate;
    private Integer months;
    private Long futureValueCents;
}
