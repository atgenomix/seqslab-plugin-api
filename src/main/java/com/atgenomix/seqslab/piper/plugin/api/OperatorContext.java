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
import java.util.Collections;
import java.util.Map;

/**
 * An object used in pipeline operations to represent a persistent set of properties, inputs,
 * and outputs. An operator context object contains a list of properties in the form of NamedValue objects.
 * These properties represent information about the operator configuration, the environment, or the
 * states of pipeline operations.
 * An operator context object is created first from the task pipeline configuration of an input or output
 * data file and then calling each operator in the pipeline execution on the default and updated context.
 * The property keys might be fully qualified by prefixing the operator name, ex: name:key.
 *
 * @see Operator
 */
public abstract class OperatorContext implements Serializable {

    /**
     * A property map that contains NamedValue objects including those specific to the operator and
     * other properties set by upstream pipeline operators.
     * The keys might be fully qualified by prefixing the operator name, ex: name:key.
     */
    protected Map<String, Object> properties;

    /**
     * An immutable map that contains NamedValue task input variables and files.
     */
    public Map<String, PiperValue> inputs;

    /**
     * An immutable map that contains NamedValue task output variables and files.
     */
    public Map<String, PiperValue> outputs;

    /**
     * Creates an operator context with the specified defaults.
     * @param properties default properties
     */
    protected OperatorContext(Map<String, Object> properties) {
        this.properties = properties;
        this.inputs = Collections.emptyMap();
        this.outputs = Collections.emptyMap();
    }

    /**
     * Creates an operator context with the specified defaults and task inputs.
     * @param properties default properties
     * @param inputs task input variables and files
     */
    protected OperatorContext(Map<String, Object> properties,
                              Map<String, PiperValue> inputs) {
        this.properties = properties;
        this.inputs = Collections.unmodifiableMap(inputs);
        this.outputs = Collections.emptyMap();
    }

    /**
     * Creates an operator context with the specified defaults and task inputs and outputs.
     * @param properties default properties
     * @param inputs task input variables and files
     * @param outputs task output variables and files
     */
    protected OperatorContext(Map<String, Object> properties,
                              Map<String, PiperValue> inputs,
                              Map<String, PiperValue> outputs) {
        this.properties = properties;
        this.inputs = Collections.unmodifiableMap(inputs);
        this.outputs = Collections.unmodifiableMap(outputs);
    }

    /**
     * Get the property value
     * @param key the property key
     * @return the property object. return null if the key is not found.
     */
    public Object get(String key) {
        return properties.get(key);
    }

    /**
     * Get the properties
     * @return the property map
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Set new properties
     */
    public void setProperties(Map<String, Object> properties) {
        properties.forEach((k, v) -> this.properties.merge(k, v, (v1, v2) -> v1));
    }

    /**
     * Get fqn
     */
    public abstract String getFqn();

    /**
     * Get DataSource
     */
    public abstract DataSource getDataSource();
}
