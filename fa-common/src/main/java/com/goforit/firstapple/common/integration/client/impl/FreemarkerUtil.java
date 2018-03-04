package com.goforit.firstapple.common.integration.client.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by goforit on 17/4/13.
 */
public class FreemarkerUtil {

    private final static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setClassForTemplateLoading(FreemarkerUtil.class, "/ftl");
        configuration.setClassicCompatible(true);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public static String process(String templateName, Object dataModel) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName);
        Writer writer = new StringWriter();
        template.process(dataModel, writer);
        writer.flush();
        return writer.toString();
    }
}
