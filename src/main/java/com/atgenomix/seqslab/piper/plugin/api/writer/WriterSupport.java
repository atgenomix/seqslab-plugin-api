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

import com.atgenomix.seqslab.piper.plugin.api.PluginContext;
import com.atgenomix.seqslab.piper.plugin.api.OperatorContext;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;

/**
 * Factory object responsible for creating {@link Writer} operators. A writer operator will be
 * requested for attached output file of workflow tasks when task command execution is successfully completed.
 * Therefore, factory might implement singleton writer object or create a new writer object
 * each time when requested depending on the implementation, e.g. thread safety.
 */
@DeveloperApi
public interface WriterSupport {

    /**
     * Returns a writer operator with specific plugin context and operator context that is created
     * and initialized by SeqsLab workflow engine.
     * @param pluginContext The plugin context returned from plugin initialization
     * @param operatorContext The operator context containing configuration parameters and task outputs
     * @return A writer object
     */
    Writer createWriter(PluginContext pluginContext, OperatorContext operatorContext);
}
