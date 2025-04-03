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

package com.atgenomix.seqslab.piper.plugin.api.collector;

import com.atgenomix.seqslab.piper.plugin.api.Operator;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.api.java.UDF0;
import org.apache.spark.sql.types.StructType;

import java.io.FileInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * The operator responsible for retrieving command outputs from local file system and returning a DataFrame.
 * Additionally, it can be responsible for computing aggregates of command outputs.
 * Collectors are a user-defined function (UDF) that takes no input parameter and returns the command output
 * as a DataFrame read from local files.
 * Collector objects are created first by invoking Collector operator factory (implements {@link CollectorSupport})
 * when pipeline task requests it and will be lazily initialized when it is ready to run.
 * When completed, the close method will be invoked to release resources.
 *
 * SeqsLab supports multiple data processing features to manage and optimize workloads.
 * A Collector can inform SeqsLab its supporting features by implementing the specific mix-in interfaces.
 *
 * @see SupportsAggregation
 * @see SupportsRepartitioning
 */
@DeveloperApi
public interface Collector extends Operator, UDF0<Iterator<Row>> {

    /**
     * Initializes this collector operator.
     * @param isDirectory true if the task output to collect from is in a directory; otherwise, false
     * @return The object itself
     */
    Collector init(boolean isDirectory);

    /**
     * Set an input stream object to obtain input bytes from a task output file.
     * @param in The underlying file input stream
     */
    void setInputStream(FileInputStream in);

    /**
     * When task output to collect from is in a directory, set the object to iterate
     * over the entries in an output directory.
     * @param dir The underlying directory stream
     */
    void setDirectoryStream(DirectoryStream<Path> dir);

    /**
     * Returns the actual schema of this collected dataset, which may be different from the
     * physical schema of the command outputs, as column pruning or other optimizations may happen.
     * @return The schema as a StructType
     */
    StructType schema();

}
