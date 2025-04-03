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
 * copying storage files or directories directly to local file system.
 * Loaders supporting this interface typically localize datasets that do not require
 * in-memory processing optimization, e.g. reference files.
 * This feature is invoked before calling operator function.
 */
@DeveloperApi
@FeatureBeforeCall
public interface SupportsCopyToLocal extends Loader {

    /**
     * Sets the local destination path where the datasets will be saved.
     * @param path Path of local file system
     * @return The same or updated local path
     */
    String setLocalPath(String path);
}
