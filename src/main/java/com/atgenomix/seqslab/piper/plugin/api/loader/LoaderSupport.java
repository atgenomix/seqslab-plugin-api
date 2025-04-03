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

import com.atgenomix.seqslab.piper.plugin.api.OperatorContext;
import com.atgenomix.seqslab.piper.plugin.api.PluginContext;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;

/**
 * Factory object responsible for creating {@link Loader} operators. A loader operator will be
 * requested for each input dataset of workflow tasks; therefore, factory might implement singleton
 * loader object or create a new loader object each time when requested depending on the implementation,
 * e.g. thread safety.
 */
@DeveloperApi
public interface LoaderSupport {

    /**
     * Returns a loader operator with specific plugin context and operator context that is created
     * and initialized by SeqsLab workflow engine.
     * @param pluginContext Plugin context returned from plugin initialization
     * @param operatorContext Operator context containing configuration parameters and task inputs
     * @return A loader object
     */
    Loader createLoader(PluginContext pluginContext, OperatorContext operatorContext);
}
