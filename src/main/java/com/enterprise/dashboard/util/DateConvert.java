package com.enterprise.dashboard.util;

import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The type Date convert.
 */
public class DateConvert {
    /**
     * Gets sql date.
     *
     * @param date   the date
     * @param logger the logger
     * @return the sql date
     */
    public static java.sql.Date getSqlDate(String date, Logger logger) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            java.util.Date utilDate = sdf.parse(date);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            logger.error(e);
        }
        return sqlDate;
    }
}
