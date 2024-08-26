package com.wind.LogsToRedis_329;

import com.wind.LogsToRedis_329.serilogj.core.DestructuringPolicyResult;
import com.wind.LogsToRedis_329.serilogj.core.IDestructuringPolicy;
import com.wind.LogsToRedis_329.serilogj.core.ILogEventPropertyValueFactory;

public class UserDestructor implements IDestructuringPolicy {
    @Override
    public DestructuringPolicyResult tryDestructure(Object value, ILogEventPropertyValueFactory propertyValueFactory) {
        DestructuringPolicyResult result = new DestructuringPolicyResult();
        result.isValid = true;
        return result;
    }
}
