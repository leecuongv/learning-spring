package com.cuonglv.learning_spring.utility.helper;

public class ProcedureHelper {

	public static String generateSpParams(String... values) {

		StringBuilder params = new StringBuilder();

		for (int i = 0; i < values.length; i++) {
			Object value = StringHelper.hasContent(values[i]) ? values[i] : null;
			params.append(String.format("%s|", value));
		}

		return params.substring(0, params.length() - 1);

	}
}
