package dev.chermenin.dao.countquerytests;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.Before;


public class CountQueryTest {

    @Before
    public void setUp() {
        SQLStatementCountValidator.reset();
    }
}
