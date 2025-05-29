package com.rest.service.restful.web.services.locale.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldSource {
	
	private MessageSource messageSource;
	
	public HelloWorldSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping("/hello-world-i18n")
	public String getI18nMessage() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("message", null, locale);
	}


}
