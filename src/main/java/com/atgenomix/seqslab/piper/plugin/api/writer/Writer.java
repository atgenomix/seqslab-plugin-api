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

package com.atgenomix.seqslab.piper.plugin.api.writer;

import com.atgenomix.seqslab.piper.plugin.api.DataSource;
import com.atgenomix.seqslab.piper.plugin.api.Operator;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.api.java.UDF2;

/**
 * The operator responsible for delocalizing/saving command output DataFrame to storage or
 * repositories, e.g. cloud file system, HTTPS repository, or JDBC database.
 * A Writer is a user-defined function (UDF) that takes a DataFrame as input and a boolean parameter,
 * true when the input DataFrame has been aggregated.
 * Writer objects are created first by invoking Writer operator factory (implements {@link WriterSupport})
 * when pipeline task requests it and will be lazily initialized when DataFrame is ready to be delocalized.
 * When completed, the close method will be invoked to release resources.
 *
 * SeqsLab supports multiple data writing features to manage and optimize workloads.
 * Operators can inform SeqsLab its supporting features by implementing the specific mix-in interfaces.
 *
 * @see SupportsSaveToBLOB
 * @see SupportsSaveToHTTP
 * @see SupportsSaveToJDBC
 */
@DeveloperApi
public interface Writer extends Operator, UDF2<Dataset<Row>, Boolean, Void> {

    /**
     * Initializes this writer operator.
     * @return The object itself
     */
    Writer init();

    /**
     * Obtains the data access information after the output DataFrame is successfully saved.
     * The object will be registered in SeqsLab Data Hub and be used for successive tasks' inputs.
     * @return A DataSource object
     */
    DataSource getDataSource();
}
