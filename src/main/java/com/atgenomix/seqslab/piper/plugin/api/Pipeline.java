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

package com.atgenomix.seqslab.piper.plugin.api;


import com.atgenomix.seqslab.piper.tags.DeveloperApi;

import java.util.Map;

/**
 * An interface represents a complete configuration of workflow task
 * including input parameters, dataset connection, as well as localization, computation,
 * and delocalization workload processes.
 */
@DeveloperApi
public interface Pipeline {

    /**
     * Get the settings of Task inputs.
     */
    Map<String, PiperValue> getInputs();

    /**
     * Get the settings of Task outputs.
     */
    Map<String, PiperValue> getOutputs();

    /**
     * Get the settings of a pipeline task.
     */
    Map<String, PipelineTask> getTasks();
}
