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

package com.atgenomix.seqslab.piper.plugin.api.transformer;

import com.atgenomix.seqslab.piper.plugin.api.Operator;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;

import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * The operator responsible for repartitioning, and additionally sorting, DataFrames loaded by
 * {@link com.atgenomix.seqslab.piper.plugin.api.loader.Loader Loader} to optimize downstream data processing.
 * For instance, in genome sequencing analysis, a transformer can repartition BAM or VCF datasets based on
 * non-overlapping target regions.
 * A Transformer is a user-defined function (UDF) that takes a DataFrame as an input parameter
 * and returns a partitioned and optionally sorted DataFrame.
 * Transformer objects are created first by invoking Transformer operator factory (implements
 * {@link TransformerSupport}) when pipeline task requests it and will be lazily initialized when it is ready to run.
 * When completed, the close method will be invoked to release resources.
 *
 * SeqsLab supports multiple data processing features to manage and optimize workloads.
 * A Transformer can inform SeqsLab its supporting features by implementing the specific mix-in interfaces.
 *
 * @see SupportsOrdering
 * @see SupportsPartitioner
 */
@DeveloperApi
public interface Transformer extends Operator, UDF1<Dataset<Row>, Dataset<Row>> {

    /**
     * Initializes this operator.
     * @param cpuCores Total number of CPU cores in current computing cluster
     * @param memPerCore Allocated memory per CPU core in GB
     * @return The object itself
     */
    Transformer init(int cpuCores, int memPerCore);

    /**
     * Get the number of partitions after repartition.
     * @return Number of partitions
     */
    int numPartitions();
}
