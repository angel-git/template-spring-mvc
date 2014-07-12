package com.ags.my.processor;

import com.ags.my.utils.TemplateUtils;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Angel
 * @since 19/06/2014
 */
public class IncludeProcessor extends AbstractProcessor {

    private static final Pattern INCLUDE_PATTERN = Pattern.compile("@\\{([^\\}]+)\\}");
    private String prefix;
    private String suffix;

    public IncludeProcessor() {
        super(INCLUDE_PATTERN);
    }

    @Override
    protected String processValue(String value) throws IOException {
        String file;
        if (value.contains(".")) {
            String[] folders = value.split("\\.");
            String path = "";
            for (int i=0; i < folders.length-1; i++ ) {
                path = path.concat(folders[i]).concat("/");
            }
            file = path + folders[folders.length-1];
        } else {
            file = value;
        }

        String templateToInclude = prefix + file + suffix;

        return TemplateUtils.getContent(servletContext, templateToInclude);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
