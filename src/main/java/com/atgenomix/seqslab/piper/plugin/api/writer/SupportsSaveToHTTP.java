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

import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import com.atgenomix.seqslab.piper.tags.FeatureBeforeCall;

import java.util.Properties;

/**
 * The mix-in interface for {@link Writer}. Dataset writers can implement this interface to
 * support putting the content of the DataFrame to a remote HTTP/HTTPS repository.
 */
@DeveloperApi
@FeatureBeforeCall
public interface SupportsSaveToHTTP extends Writer {

    /**
     * Sets the target HTTP connection with which the DataFrame will be saved.
     * @param url HTTP/HTTPS url
     * @param connectionProperties Connection arguments and headers, a list of arbitrary string key/value
     * @return The writer object itself
     */
    Writer save(String url, Properties connectionProperties);
}
