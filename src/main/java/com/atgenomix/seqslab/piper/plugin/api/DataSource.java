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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The main interface responsible for representing an accessible data source in SeqsLab.
 * The underlying data source acts as the canonical object in SeqsLab Data Hub service.
 */
@DeveloperApi
public class DataSource extends Properties {

    /**
     * Retrieve type of the access method to the data source, e.g. https, abfss, s3, etc.
     * @return Access type
     */
    public String getType() {
        return getProperty("type");
    }

    /**
     * Retrieve the access URL to the datasets.
     * @return Data source URL
     */
    public String getUrl() {
        return getProperty("url");
    }

    /**
     * Retrieve the access options associated with this data source, e.g. HTTPS Authentication header.
     * @return A map containing a list of named option values
     */
    public Map<String, String> getOptions() {
        Map<String, String> options = new HashMap<>();
        this.forEach((key, value) -> {
            if (((String)key).startsWith("option.")) {
                options.put(((String)key).substring("option.".length()), (String)value);
            }
        });
        return options;
    }

    /**
     * Retrieve the data center region, from which the data source will be accessed.
     * Depending on the data source implementations, the region might not be available.
     * @return Data center region
     */
    public String getRegion() {
        return getProperty("region");
    }

    /**
     * Retrieve the updated time of the data source, e.g. 2023-02-23T03:27:50.967Z.
     * Delta table data can use updated time to realize Time Travel.
     * @return Updated time
     */
    public String getUpdatedTime() {
        return getProperty("updatedTime");
    }
}
