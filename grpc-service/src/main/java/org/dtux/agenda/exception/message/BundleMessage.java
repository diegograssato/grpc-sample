package org.dtux.agenda.exception.message;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

@Slf4j
@NoArgsConstructor
public class BundleMessage {

    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    private static final String BASE_NAME = "messages/messages";
    private static final String UTF8 = "UTF-8";

    public static String getMessage(final String tag, final String[] args) {
        try {
            messageSource.setDefaultEncoding(UTF8);
            messageSource.setBasename(BASE_NAME);
            return messageSource.getMessage(tag, args, LocaleContextHolder.getLocale());
        } catch (final NoSuchMessageException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }
}
