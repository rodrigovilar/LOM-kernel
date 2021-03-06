package com.nanuvem.lom.business.validator;

import java.util.List;

import com.nanuvem.lom.business.validator.configuration.PropertyTypeValidator;
import com.nanuvem.lom.business.validator.configuration.ConfigurationFieldValidator;

public class MinimumSymbolsPropertyTypeConfigurationValidator implements
		ValueValidator<Integer> {

	public void validate(List<ValidationError> errors, String attribute,
			String value, Integer minSymbols, boolean defaultValue) {

		if (value != null) {
			int numericSymbolCounter = 0;
			for (int i = 0; i < value.length(); i++) {
				if (!Character.isLetterOrDigit(value.toCharArray()[i])) {
					numericSymbolCounter++;
				}
			}

			if (numericSymbolCounter < minSymbols) {
				String messagePlural = minSymbols > 1 ? "s" : "";

				String message = (defaultValue) ? "the default value must have at least "
						+ minSymbols + " symbol character" + messagePlural
						: "The value for '" + attribute
								+ "' must have at least " + minSymbols
								+ " symbol character" + messagePlural;

				ValidationError.addError(errors, message);
			}
		}
	}

	public PropertyTypeValidator createFieldValidator(String field) {
		return new ConfigurationFieldValidator(field, Integer.class);
	}

}
