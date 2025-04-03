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
 * A mix-in interface for {@link Transformer}. Dataset transformer can implement this interface to support sorting for
 * ordering-aware processing that SeqsLab shall assure the same sorting order of all partitions of DataFrame
 * must be preserved in the downstream localization process.
 */
@DeveloperApi
@FeatureAfterCall
public interface SupportsOrdering extends Transformer {

    /**
     * Get the sorting expressions within each partition, ex: df.col("seq_id").asc().
     * @return Array of DataFrame Columns
     */
    Column[] getSortExprs(Dataset<Row> df);
}
