package com.ags.my.template;

import com.ags.my.processor.IncludeProcessor;
import com.ags.my.processor.MessageSourceProcessor;
import com.ags.my.processor.ModelProcessor;
import com.ags.my.utils.TemplateUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

/**
 * @author Angel
 * @since 19/06/2014
 */
public class MyTemplateEngine implements ITemplateEngine {

    private String prefix;
    private String suffix;

    //TODO move this somewhere else
    private MessageSource messageSource;
    private IncludeProcessor includeProcessor;
    private MessageSourceProcessor messageSourceProcessor;
    private ModelProcessor modelProcessor;


    public MyTemplateEngine() {
        includeProcessor = new IncludeProcessor();
        messageSourceProcessor = new MessageSourceProcessor();
        modelProcessor = new ModelProcessor();
    }

    @Override
    public void runTemplate(String templateName, Map<String, ?> model, ServletContext servletContext, PrintWriter writer, Locale locale) {

        prepareProcessors(servletContext, locale, model);
        try {
            String fileContent = TemplateUtils.getContent(servletContext, prefix + templateName + suffix);
            fileContent = includeProcessor.applyProcessor(fileContent);
            fileContent = modelProcessor.applyProcessor(fileContent);
            fileContent = messageSourceProcessor.applyProcessor(fileContent);

            writer.append(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void prepareProcessors(ServletContext servletContext, Locale locale, Map<String, ?> model) {
        includeProcessor.setServletContext(servletContext);
        includeProcessor.setPrefix(prefix);
        includeProcessor.setSuffix(suffix);
        messageSourceProcessor.setMessageSource(messageSource);
        messageSourceProcessor.setLocale(locale);
        modelProcessor.setModel(model);
    }

    @Required
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Required
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


}
