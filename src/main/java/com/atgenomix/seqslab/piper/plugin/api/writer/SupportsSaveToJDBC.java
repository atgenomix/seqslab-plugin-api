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
 * support saving the content of the DataFrame to an external database table via JDBC.
 * This feature is invoked before applying Writer's call function.
 */
@DeveloperApi
@FeatureBeforeCall
public interface SupportsSaveToJDBC extends Writer {

    /**
     * Sets the target database connection with which the DataFrame will be saved.
     * @param url JDBC database url of the form jdbc:subprotocol:subname
     * @param table Name of the table in the external database.
     * @param connectionProperties Connection arguments, a list of arbitrary string key/value
     * @return The writer object itself
     */
    Writer save(String url, String table, Properties connectionProperties);
}
