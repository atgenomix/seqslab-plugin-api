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

/**
 * A mix-in interface for {@link Loader}. Dataset loaders can implement this interface to support
 * partition-aware dataset reading concurrently. SeqsLab runs the Loader in parallel and ensure
 * the same data partition across multiple input data sources are in the same command execution
 * in localization process.
 */
@DeveloperApi
@FeatureBeforeCall
public interface SupportsReadPartitions extends Loader {

    /**
     * Get the number of partitions in the data source.
     * @return Number of partitions
     */
    int numPartitions();

    /**
     * Set the target partition that this loader is responsible loading from
     * input data source.
     * @param partitionId Partition identifier as an integer
     */
    void setPartitionId(int partitionId);
}
