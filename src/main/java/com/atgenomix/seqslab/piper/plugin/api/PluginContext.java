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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * An object returned when initializing this SeqsLab piper. It stores {@link PiperContext}
 * and represents a persistent set of plugin-specific properties, which will be passed to registered
 * operator factory to create the plugin-specific pipeline operators.
 *
 * @see OperatorContext
 */
public class PluginContext implements Serializable {

    /**
     * An object used in all operations of a workflow task execution.
     */
    public final PiperContext piper;

    /**
     * A persistent set of plugin-specific properties.
     */
    protected Map<String, Object> properties;

    /**
     * Creates a plugin context object with Seqslab piper context.
     * @param piperContext A PiperContext object.
     */
    public PluginContext(PiperContext piperContext) {
        piper = piperContext;
        properties = new HashMap<>();
    }

    /**
     * Calls the Hashtable method get.
     * @param key The property key.
     * @return The value of the specified key.
     */
    public Object getProperty(String key) {
        return this.properties.get(key);
    }

    /**
     * Calls the Hashtable method put. The value returned is the result of the Hashtable call to put.
     * @param key The key to be placed into this PluginContext property list.
     * @param value The value corresponding to the key.
     * @return The result of the Hashtable put call.
     */
    public Object setProperty(String key, Object value) {
        return this.properties.put(key, value);
    }
}
