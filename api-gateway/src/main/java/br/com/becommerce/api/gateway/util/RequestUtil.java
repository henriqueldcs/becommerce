package br.com.becommerce.api.gateway.util;

import java.util.UUID;

public class RequestUtil {

	public static String generateRequestUUID() {
		return UUID.randomUUID().toString();
	}
}
