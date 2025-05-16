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
import com.google.gson.JsonObject;


/**
 * An interface represents the localization, computation, and delocalization workload processes
 * in a pipeline task of a given variable identified by FQN (Fully Qualified Name).
 * @see Pipeline
 */
@DeveloperApi
public interface PipelineTask {

    /**
     * Get the list of Operators applied on given input or output FQN.
     * @return a list of JsonObject
     */
     public JsonObject[] getOperators();
}
