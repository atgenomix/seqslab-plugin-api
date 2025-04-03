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

package com.atgenomix.seqslab.piper.plugin.api.executor;

import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import com.atgenomix.seqslab.piper.tags.FeatureAfterCall;
import com.atgenomix.seqslab.piper.tags.FeatureBeforeCall;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.api.java.UDF1;

import java.util.Collections;
import java.util.Map;

/**
 * A mix-in interface for {@link Executor}. Executors can implement this interface to support DataFrame preprocessing
 * for SQL command execution. If the File variable is defined as a Struct with fields in WDL, SeqsLab automatically
 * renames table columns to the Struct field names.
 *
 * Executors implementing this mix-in interface is a user-defined function (UDF) that takes a DataFrame as an input
 * parameter and returns a new DataFrame.
 * This feature is invoked before and after applying Executor's call function.
 */
@DeveloperApi
@FeatureBeforeCall
@FeatureAfterCall
public interface SupportsTableLocalization extends Executor, UDF1<Dataset<Row>, Dataset<Row>> {

    /**
     * Assign table name in the metastore using input dataframe's schema.
     * This method is called before operator invocation.
     * @param name Table name with namespace
     */
    void setTableName(String name);

    /**
     * Retrieves one or more NamedValue properties to set into the delta table.
     * This method is called after operator invocation.
     * @return A map of properties as String
     */
    default Map<String, String> getProperties() {
        return Collections.emptyMap();
    }
}
