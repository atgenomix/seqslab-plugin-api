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

package com.atgenomix.seqslab.piper.plugin.api.collector;

import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import com.atgenomix.seqslab.piper.tags.FeatureAfterCall;

/**
 * A mix-in interface for {@link Collector}. Command output collectors can implement this interface to support
 * computing aggregates and returns the aggregated results as a DataFrame.
 * When collector supports aggregation, SeqsLab calls the instance method agg() to aggregate on the entire DataFrame.
 * This feature is invoked after applying Collector's call function.
 */
@DeveloperApi
@FeatureAfterCall
public interface SupportsRepartitioning extends Collector {

    /**
     * Get the number of partitions in the collected dataframe.
     * @return Number of partitions
     */
    int numPartitions();
}
