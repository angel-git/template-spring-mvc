package com.ags.my.template;

import javax.servlet.ServletContext;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

/**
 * @author Angel
 * @since 19/06/2014
 */
public interface ITemplateEngine {


    public void runTemplate(String templateName, Map<String, ?> model, ServletContext servletContext, PrintWriter writer, Locale locale);
}
