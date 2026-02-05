package com.xiaoxu.rabbitmq_demo.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingConverter extends ClassicConverter {
    private static final Pattern PHONE_PATTERN = Pattern.compile("(1\\d{2})\\d{4}(\\d{4})");
    private static final Pattern ID_PATTERN = Pattern.compile("(\\d{4})\\d{10}(\\w{4})");

    @Override
    public String convert(ILoggingEvent event) {
        String message = event.getFormattedMessage();
        return maskSensitive(message);
    }

    private String maskSensitive(String message) {
        if (message == null || message.isBlank()) {
            return message;
        }
        String masked = maskByPattern(message, PHONE_PATTERN, 1, 2);
        masked = maskByPattern(masked, ID_PATTERN, 1, 2);
        return masked;
    }

    private String maskByPattern(String message, Pattern pattern, int prefixGroup, int suffixGroup) {
        Matcher matcher = pattern.matcher(message);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String replacement = matcher.group(prefixGroup) + "****" + matcher.group(suffixGroup);
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
