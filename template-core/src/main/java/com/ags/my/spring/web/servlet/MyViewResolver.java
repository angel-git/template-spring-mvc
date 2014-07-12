package com.ags.my.spring.web.servlet;

import com.ags.my.template.MyTemplateEngine;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import java.util.Locale;

/**
 * @author Angel
 * @since 19/06/2014
 */
public class MyViewResolver  extends AbstractCachingViewResolver  {


    private MyTemplateEngine templateEngine;

    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        MyView view = BeanUtils.instantiateClass(MyView.class);
        final AutowireCapableBeanFactory beanFactory = getApplicationContext().getAutowireCapableBeanFactory();

        if (beanFactory.containsBean(viewName)) {
            final Class<?> viewBeanType = beanFactory.getType(viewName);
            // viewBeanType could be null if bean is abstract (just a set of properties)
            if (viewBeanType != null && MyView.class.isAssignableFrom(viewBeanType)) {
                view = (MyView) beanFactory.configureBean(view, viewName);
            } else {
                // The AUTOWIRE_NO mode applies autowiring only through annotations
                beanFactory.autowireBeanProperties(view, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
                // A bean with this name exists, so we apply its properties
                beanFactory.applyBeanPropertyValues(view, viewName);
                // Finally, we let Spring do the remaining initializations (incl. proxifying if needed)
                view = (MyView) beanFactory.initializeBean(view, viewName);
            }
        } else {
            // The AUTOWIRE_NO mode applies autowiring only through annotations
            beanFactory.autowireBeanProperties(view, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
            // Finally, we let Spring do the remaining initializations (incl. proxifying if needed)
            view = (MyView) beanFactory.initializeBean(view, viewName);
        }

        view.setTemplateEngine(templateEngine);
        view.setTemplateName(viewName);
        view.setLocale(locale);
        return view;
    }


    public void setTemplateEngine(MyTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
}
