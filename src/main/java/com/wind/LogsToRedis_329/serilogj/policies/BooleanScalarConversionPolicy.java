package com.wind.LogsToRedis_329.serilogj.policies;

import com.wind.LogsToRedis_329.serilogj.core.ILogEventPropertyValueFactory;
import com.wind.LogsToRedis_329.serilogj.core.IScalarConversionPolicy;
import com.wind.LogsToRedis_329.serilogj.core.ScalarConversionPolicyResult;
import com.wind.LogsToRedis_329.serilogj.events.ScalarValue;

public class BooleanScalarConversionPolicy implements IScalarConversionPolicy {
	private final static ScalarValue TrueScalarValue = new ScalarValue(true);
	private final static ScalarValue FalseScalarValue = new ScalarValue(false);

	@Override
	public ScalarConversionPolicyResult tryConvertToScalar(Object value,
			ILogEventPropertyValueFactory propertyValueFactory) {
		ScalarConversionPolicyResult result = new ScalarConversionPolicyResult();

		ScalarValue resultValue = null;
		try {
			if (value instanceof Boolean) {
				resultValue = ((Boolean) value) ? TrueScalarValue : FalseScalarValue;
			} else if (value.getClass().isPrimitive() && value.getClass().getName().equals("boolean")) {
				resultValue = ((boolean) value) ? TrueScalarValue : FalseScalarValue;
			}
		} catch (Exception e) {
			// We ignore this error
		}

		result.isValid = resultValue != null;
		return result;
	}
}
