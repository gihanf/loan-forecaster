package com.gihan.convertor;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import com.mysql.jdbc.StringUtils;

public final class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter formatter;

    public StringToLocalDateConverter(String dateFormat) {
        this.formatter = DateTimeFormat.forPattern(dateFormat);
    }

    @Override
    public LocalDate convert(String dateString) {
        LocalDate parsedDate;
        if (StringUtils.isNullOrEmpty(dateString)) {
            return null;
        }
        try {
            parsedDate = LocalDate.parse(dateString, formatter);
        } catch(RuntimeException e) {
            throw new IllegalArgumentException("Date string was not in the correct format");
        }
        return parsedDate;
    }
}