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

import java.util.Map;

/**
 * A mix-in interface for {@link Loader}. Dataset loaders can implement this interface to
 * indicate it supports reading files and directories from storages compatible with Hadoop Distributed File System.
 * SeqsLab will try to obtain and pass hdfs://authority/path source URI when initializing the loader.
 */
@DeveloperApi
@FeatureBeforeCall
public interface SupportsHadoopDFS extends Loader {

    /**
     * Sets Hadoop configuration
     * @param configuration Configuration properties as a Map
     */
    void setConfiguration(Map<String, String> configuration);
}
