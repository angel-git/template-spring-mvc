package com.ags.my.processor;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Angel
 * @since 20/06/2014
 */
public class ModelProcessor extends AbstractProcessor {

    private static final Pattern MODEL_PATTERN = Pattern.compile("#\\{([^\\}]+)\\}");
    private Map<String, ?> model;

    public ModelProcessor() {
        super(MODEL_PATTERN);
    }

    @Override
    protected String processValue(String value) throws IOException {
        return String.valueOf(model.get(value));
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
