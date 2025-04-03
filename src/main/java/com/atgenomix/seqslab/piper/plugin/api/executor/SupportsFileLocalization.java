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
import com.atgenomix.seqslab.piper.tags.FeatureBeforeCall;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.api.java.UDF1;

import java.util.Iterator;

/**
 * A mix-in interface for {@link Executor}. Executors can implement this interface to support writing input dataframe
 * to local file system for shell script execution.
 *
 * Executors implementing this mix-in interface is a user-defined function (UDF) that takes a DataFrame partition
 * as an input parameter and returns the localization result as an Integer, which 0 means successful.
 * This feature is invoked before applying Executor's call function.
 */
@DeveloperApi
@FeatureBeforeCall
public interface SupportsFileLocalization extends Executor, UDF1<Iterator<Row>, Integer> {

    /**
     * Sets the local destination path which the datasets will be written to.
     * @param path File or directory path
     * @return The same or updated local path
     */
    String setLocalPath(String path);
}
