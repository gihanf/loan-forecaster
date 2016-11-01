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
        if (StringUtils.isNullOrEmpty(dateString)) {
            return null;
        }
        return LocalDate.parse(dateString, formatter);
    }
}