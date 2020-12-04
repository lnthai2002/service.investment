package info.noip.darkportal.service.investment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
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
@Accessors(chain = true, fluent = true)
public class Investment {
    @Id
    @GeneratedValue
    private UUID investmentId;
    private Long principalCents;
    private Long monthlyDepCents;
    private BigDecimal rate;
    private Integer months;

    //these auditing fields are used by the framwwork, their setter/getter cannot be fluent.
    //Thus I have to disable lombok on these and create standard setter/getter for them
    @CreatedDate
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Long createdAt;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Long updatedAt;

    public Long getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    protected void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}