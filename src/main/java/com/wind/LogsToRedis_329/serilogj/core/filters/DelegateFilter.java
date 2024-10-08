package com.wind.LogsToRedis_329.serilogj.core.filters;

import com.wind.LogsToRedis_329.serilogj.core.ILogEventFilter;
import com.wind.LogsToRedis_329.serilogj.events.*;

import java.util.function.Function;

// Copyright 2013-2015 Serilog Contributors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

public class DelegateFilter implements ILogEventFilter {
	private Function<LogEvent, Boolean> isEnabled;

	public DelegateFilter(Function<LogEvent, Boolean> isEnabled) {
		if (isEnabled == null) {
			throw new IllegalArgumentException("isEnabled");
		}

		this.isEnabled = isEnabled;
	}

	@Override
	public boolean isEnabled(LogEvent logEvent) {
		if (logEvent == null) {
			throw new IllegalArgumentException("logEvent");
		}

		return isEnabled.apply(logEvent);
	}
}
