package com.ags.my.spring.web.servlet;

import com.ags.my.template.ITemplateEngine;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;

import java.util.Locale;
import java.util.Map;

/**
 * @author Angel
 * @since 19/06/2014
 */
public class MyView extends WebApplicationObjectSupport implements View {

    private ITemplateEngine templateEngine;
    private String templateName;
    private Locale locale;


    @Override
    public String getContentType() {
        return "text/html";
    }


    @Override
    public void render(Map<String, ?> model, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        templateEngine.runTemplate(templateName, model, getServletContext(), response.getWriter(), locale);
    }

    public void setTemplateEngine(ITemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
