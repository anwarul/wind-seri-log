package com.wind.LogsToRedis_329.serilogj.events;

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

/**
 * Descriptive aliases for <see cref="LogEventLevel"/>.
 * 
 * These do not appear as members of the enumeration as duplicated underlying
 * values result in issues when presenting enum values with
 * <see cref="object.ToString()"/>.
 */
public final class LevelAlias {
	/**
	 * The least significant level of event.
	 */
	public static final LogEventLevel Minimum = LogEventLevel.Verbose;

	/**
	 * The most significant level of event.
	 */
	public static final LogEventLevel Maximum = LogEventLevel.Fatal;
}