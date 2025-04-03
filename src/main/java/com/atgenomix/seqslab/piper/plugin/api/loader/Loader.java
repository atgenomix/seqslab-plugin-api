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

package com.atgenomix.seqslab.piper.plugin.api.loader;

import com.atgenomix.seqslab.piper.plugin.api.DataSource;
import com.atgenomix.seqslab.piper.plugin.api.Operator;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.api.java.UDF0;
import org.apache.spark.sql.types.StructType;

import java.util.Iterator;

/**
 * The operator responsible for loading (reading) a dataset into in-memory DataFrame or copying to local host
 * file system from a specific data source, e.g. blob storage.
 * A Loader is a user-defined function (UDF) that takes no parameter and returns an array of data rows as
 * an iterator when required.
 * Loader objects are created first by invoking Loader operator factory (implements {@link LoaderSupport})
 * when pipeline task requests it and will be lazily initialized when it is ready to run. When completed, the
 * close method will be invoked to release resources.
 *
 * SeqsLab supports multiple data processing features to manage and optimize workloads.
 * An operator can inform SeqsLab its supporting features by implementing the specific mix-in interfaces.
 * For instance, a loader implementing {@link SupportsCopyToLocal} can localize, e.g.
 * genome reference files, to all available computing nodes; the loader can additionally implement
 * {@link SupportsReadPartitions} to tell SeqsLab the localization is partition-aware and can read each data
 * partition concurrently and individually. SeqsLab will run the Loader in parallel and ensure the same data
 * partition across multiple input data sources are in the same command execution in localization process.
 *
 * @see SupportsCopyToLocal
 * @see SupportsHadoopDFS
 * @see SupportsReadPartitions
 * @see SupportsScanPartitions
 */
@DeveloperApi
public interface Loader extends Operator, UDF0<Iterator<Row>> {

    /**
     * Initializes this operator with a specific data source.
     * @param source Connection arguments of a task input data source
     * @return The object itself
     */
    Loader init(DataSource source);

    /**
     * Returns the actual schema of this dataset loader, which may be different from the
     * physical schema of the source storage, as column pruning or other optimizations may happen.
     * @return The schema as a StructType
     */
    StructType readSchema();
}
