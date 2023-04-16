package hhsof3as3.musicdatabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MyBeansConfig {
	  @Bean
	  public LocaleResolver localeResolver() {
	      CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	      localeResolver.setDefaultLocale(Locale.ENGLISH);
	      localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC"));
	      return localeResolver;
	  }
	  
	  @Bean
	  public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	    localeChangeInterceptor.setParamName("localeData");
	    return localeChangeInterceptor;
	  }

}
