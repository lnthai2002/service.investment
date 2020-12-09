package info.noip.darkportal.service.investment.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvestmentResponseDTO implements Serializable {
    private String investmentId;
    private Long principalCents;
    private Long monthlyDepCents;
    private BigDecimal rate;
    private Integer months;
    private Long futureValueCents;
    private Long createdAt;
    private Long updatedAt;
}
