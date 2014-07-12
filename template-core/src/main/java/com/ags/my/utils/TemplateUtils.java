package com.ags.my.utils;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Angel
 * @since 19/06/2014
 */
public class TemplateUtils {

    public static String getContent(ServletContext servletContext, String file) throws IOException {
        InputStream resourceAsStream = servletContext.getResourceAsStream(file);
        return IOUtils.toString(resourceAsStream);
    }
}
