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

import com.atgenomix.seqslab.piper.plugin.api.Operator;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import org.apache.spark.sql.types.DataType;

import java.util.Map;

/**
 * An operator responsible for formatting input datasets, such as converting schema, adding or deleting columns,
 * and encoding domain specific object.
 * A Formatter is a scalar user-defined function (UDF) that acts on one DataFrame row and takes various number of
 * columns as input parameters and returns a new or existing formatted column value.
 * Formatter objects are created first by invoking Formatter operator factory (implements {@link FormatterSupport})
 * when pipeline task requests it and will be lazily initialized when it is ready to run.
 * When completed, the close method will be invoked to release resources.
 *
 * SeqsLab supports multiple data processing features to manage and optimize workloads.
 * A Formatter can inform SeqsLab its supporting features by implementing the specific mix-in interfaces.
 *
 * @see SupportsUDF1
 * @see SupportsUDF2
 * @see SupportsUDF3
 * @see SupportsUDF4
 * @see SupportsUDF5
 */
@DeveloperApi
public interface Formatter extends Operator {

    /**
     * Initializes this formatter operator.
     * @return The object itself
     */
    Formatter init();

    /**
     * Returns a set of selected column names as an array.
     * When calling user-defined formatter function, workflow engine will pass the column values
     * in exact order as the order of the selected column names.
     */
    String[] select();

    /**
     * Returns a pair of output column name and its data type for create (new column name) or
     * update (existing column name) after calling this Formatter user-defined function.
     * The return type of Formatter UDF must match the data type return by this method.
     */
    Map.Entry<String, DataType> withColumn();
}
