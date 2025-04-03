/*
 * Copyright (c) 2021-2022, ATGENOMIX INCORPORATED.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atgenomix.seqslab.piper.plugin.api.formatter;

import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import org.apache.spark.sql.api.java.UDF4;

/**
 * A mix-in interface for {@link Formatter}. Dataset formatters can implement this interface to support
 * 4 input columns, i.e. the selected column name array from {@link Formatter#select() Select}
 * must have 4 elements.
 */
@DeveloperApi
public interface SupportsUDF4<T1, T2, T3, T4, R> extends Formatter, UDF4<T1, T2, T3, T4, R> {
}
