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
import org.apache.commons.lang3.tuple.Pair;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.net.URI;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

/**
 * An interface represents value of primitive types, e.g. Int, Double, or String, and
 * object types, e.g. array, dictionary, or struct.
 */
@DeveloperApi
public interface PiperValue {

    /**
     * Concrete types of all supported workflow parameters
     */
    enum Type {
        BOOLEAN,
        INTEGER,
        DOUBLE,
        STRING,
        FILE,
        ARRAY,
        DICTIONARY,
        DATAFRAME
    }

    /**
     * Get the concrete type of this PiperValue object.
     * @return The concrete type
     */
    PiperValue.Type getType();

    /**
     * Retrieve the value as a Boolean.
     * @return A Boolean value
     */
    default Boolean getBoolean() { return null; }

    /**
     * Retrieve the value as an Integer.
     * @return An Integer value
     */
    default Integer getInteger() { return null; }

    /**
     * Retrieve the value as a Double.
     * @return A Double value
     */
    default Double getDouble() { return null; }

    /**
     * Retrieve the value as a String.
     * @return A String value
     */
    default String getString() { return null; }

    /**
     * Retrieve the value as a Pair of path and SeqsLab DRS object.
     * @return A Pair value representing a File
     */
    default Pair<Path, Object> getFile() { return null; }

    /**
     * Retrieve the value as an array of PiperValue.
     * @return An array value
     */
    default PiperValue[] getArray() { return null; }

    /**
     * Retrieve the value as a list of NamedValue objects with key as a String.
     * @return A dictionary value
     */
    default Map<String, PiperValue> getDictionary() { return Collections.emptyMap(); }

    /**
     * Retrieve the value as a Pair of URL and its Spark Dataframe.
     * @return A Pair value representing a dataframe
     */
    default Pair<URI, Dataset<Row>> getDataframe() { return null; }
}
