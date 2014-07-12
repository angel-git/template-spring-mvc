package com.ags.my.processor;

import org.springframework.context.MessageSource;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author Angel
 * @since 20/06/2014
 */
public class MessageSourceProcessor extends AbstractProcessor {

    private static final Pattern MESSAGE_PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");
    private MessageSource messageSource;
    private Locale locale;

    public MessageSourceProcessor() {
        super(MESSAGE_PATTERN);
    }


    @Override
    protected String processValue(String value) throws IOException {
        return messageSource.getMessage(value, null, locale);
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
