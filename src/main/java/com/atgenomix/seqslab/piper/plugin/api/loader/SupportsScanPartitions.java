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

import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import com.atgenomix.seqslab.piper.tags.FeatureBeforeCall;
import org.apache.spark.sql.Row;

import java.util.Iterator;

/**
 * A mix-in interface for {@link Loader}. Dataset loaders can implement this interface to support
 * transformation of partitioned datasets read by SeqsLab.
 */
@DeveloperApi
@FeatureBeforeCall
public interface SupportsScanPartitions extends Loader {

    /**
     * Set the partition loaded by SeqsLab for applying Loader's call function.
     * @param partition Iterator for the partition
     */
    void setPartition(Iterator<Row> partition);
}
