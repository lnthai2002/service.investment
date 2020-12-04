package info.noip.darkportal.service.investment.service.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

class JpaInvestmentServiceTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void get() {
        //JpaInvestmentService delegates call to repository, nothing to test
    }

    @Test
    void save() {
        //JpaInvestmentService delegates call to repository, nothing to test
    }

    @Test
    void recalculate() {
    }
}