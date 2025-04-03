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

import com.atgenomix.seqslab.piper.plugin.api.Operator;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;

/**
 * The operator responsible for preprocessing (localizing) input dataframe as a managed table for
 * Spark SQL command or saving to local files for shell script execution.
 * Executor objects are created first by invoking Executor operator factory (implements {@link ExecutorSupport})
 * when pipeline task requests it and will be lazily initialized when it is ready to run.
 * When completed, the close method will be invoked to release resources.
 *
 * SeqsLab supports multiple data processing features to manage and optimize workloads.
 * An executor can inform SeqsLab its supporting features by implementing the specific mix-in interfaces.
 * An Executor must implement either {@link SupportsFileLocalization} or {@link SupportsTableLocalization}.
 *
 * @see SupportsFileLocalization
 * @see SupportsTableLocalization
 */
@DeveloperApi
public interface Executor extends Operator {

    /**
     * Initializes this executor operator.
     * @return The object itself
     */
    Executor init();
}