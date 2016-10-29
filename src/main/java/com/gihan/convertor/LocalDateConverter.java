package com.gihan.convertor;


import java.text.ParseException;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.Formatter;

import com.mysql.jdbc.StringUtils;

public final class LocalDateConverter implements Formatter<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateConverter(String dateFormat) {
        this.formatter = DateTimeFormat.forPattern(dateFormat);
    }

    @Override
    public String print(LocalDate date, Locale locale) {
        if (date == null) {
            return null;
        }
        return date.toString(formatter);
    }

    @Override
    public LocalDate parse(String dateString, Locale locale) throws ParseException {
        if (StringUtils.isNullOrEmpty(dateString)) {
            return null;
        }
        return LocalDate.parse(dateString, formatter);
    }
}