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

import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import com.atgenomix.seqslab.piper.tags.FeatureAfterCall;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;


/**
 * A mix-in interface for {@link Transformer}. Dataset transformer can implement this interface to
 * support context-specific dataset partitioning for pairing-aware processing of multiple datasets.
 * This is particularly useful when the dataframe hash partitioning strategy is not able to provide the
 * desired granular dataset partitions, i.e. data records belonging to different partitions would be grouped
 * into the same partition because of the same hash value. Especially when processing across multiple
 * datasets (e.g. tumor-normal somatic analysis, joint genotyping), SeqsLab uses the expressions to repartition
 * records in each dataframe partition after unioning corresponding partitions across multiple datasets
 * and properly pairing them for localization.
 */
@DeveloperApi
@FeatureAfterCall
public interface SupportsPartitioner extends Transformer {

    /**
     * Get the partitioner expression as a one or more of {@link Column}, e.g.
     *
     * array(df.col("x1"), lit(1))
     *
     * @return A Column wrapped with one or more Columns
     */
    Column expr(Dataset<Row> df);

    /**
     * Compute the partition ID for each row of column values defined by {@link #expr(Dataset)}.
     */
    Integer partitionId(Object... cols);
}
