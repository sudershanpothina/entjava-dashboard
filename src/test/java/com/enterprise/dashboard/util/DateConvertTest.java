package com.enterprise.dashboard.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static com.enterprise.dashboard.util.DateConvert.getSqlDate;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Date convert test.
 */
class DateConvertTest {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets sql date test.
     */
    @Test
    void getSqlDateTest() {

        Date date = getSqlDate("2019-05-01", logger);
        assertTrue(date instanceof Date);
    }

    /**
     * Gets sql date parse exception.
     */
    @Test
    void getSqlDateParseException() {
        Date date = getSqlDate("not a date", logger);
        assertTrue(date == null);
    }
}