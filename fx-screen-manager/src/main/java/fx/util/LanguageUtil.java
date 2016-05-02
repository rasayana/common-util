package fx.util;

import java.util.Locale;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import fx.config.Constants;

public class LanguageUtil {
	private static ReloadableResourceBundleMessageSource messageSource;
	private static Locale locale;
	private static void init() {
		messageSource = new ReloadableResourceBundleMessageSource();
		locale = (Locale)CacheUtil.getCache(Constants.LOCALE);
		if (locale == null)
			CacheUtil.putCache(Constants.LOCALE, new Locale("En"));
	}
	public static String getMessage(String messageCode, Object... args) {
		if (messageSource == null)
			init();
		messageSource.setBasename("classpath:i18n/Language");
		return messageSource.getMessage(messageCode, args, locale);
	}
}
