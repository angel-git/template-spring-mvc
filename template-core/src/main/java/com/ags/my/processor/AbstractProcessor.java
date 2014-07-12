package com.ags.my.processor;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Angel
 * @since 19/06/2014
 */
public abstract class AbstractProcessor implements Processor {

    private Pattern pattern;

    //TODO this info is coming from the template engine, duplicated??
    protected ServletContext servletContext;


    protected AbstractProcessor(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String applyProcessor(String input) throws IOException {
        String newOutput = input;

        Matcher matcher = pattern.matcher(newOutput);
        while (matcher.find()) {
            String token = matcher.group(0);
            String attribute = matcher.group(1);
            String value = processValue(attribute);
            newOutput = newOutput.replaceAll(Pattern.quote(token), value);
            matcher.reset(newOutput);

        }
        return newOutput;
    }

    /**
     * delegates what to do with the string retreived from the template
     * @param value
     * @return
     */
    protected abstract String processValue(String value) throws IOException;


    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


}
