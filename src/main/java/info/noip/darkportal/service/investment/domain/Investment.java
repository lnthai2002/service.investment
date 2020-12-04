package info.noip.darkportal.service.investment.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Investment {
    @Id
    @GeneratedValue
    private UUID investmentId;
    private Long principalCents;
    private Long monthlyDepCents;
    private BigDecimal rate;
    private Integer months;
    @CreatedDate
    private Long createdAt;
    @LastModifiedDate
    private Long updatedAt;
}