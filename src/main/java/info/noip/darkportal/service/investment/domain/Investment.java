package info.noip.darkportal.service.investment.domain;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
public class Investment {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID investmentId;
    @NonNull
    private Long principalCents;
    @NonNull
    private Long monthlyDepCents;
    /**
     * This is usually < 1*/
    @NonNull
    private BigDecimal rate;
    @NonNull
    private Integer months;

    @CreatedDate
    private Long createdAt;
    @LastModifiedDate
    private Long updatedAt;



    /**
     * Lombok will not generate the builder() method when it see this
     * Here we need to use a builder customized from the builder generated by lombok
     */
    public static InvestmentBuilder builder() {
        return new CustomInvestmentBuilder();
    }

    /**
     * This is my customized builder
     */
    private static class CustomInvestmentBuilder extends InvestmentBuilder {
        /**
         * Calculate future value when we build
         * */
        @Override
        public Investment build() {
            Investment investment = super.build();
            return investment;
        }

    }
}