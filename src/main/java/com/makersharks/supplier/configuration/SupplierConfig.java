package com.makersharks.supplier.configuration;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplierConfig {

    public static String inputSanitizer(String input) {
        if(input == null) {
            return "";
        }
        return StringEscapeUtils.escapeHtml4(input.trim());
    }

}
