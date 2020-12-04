package info.noip.darkportal.service.investment.api.v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class InvestmentResponseDTO implements Serializable {
    private String investmentId;
    private Long principalCents;
    private Long monthlyDepCents;
    private BigDecimal rate;
    private Integer months;
    private Long createdAt;
    private Long updatedAt;
}
