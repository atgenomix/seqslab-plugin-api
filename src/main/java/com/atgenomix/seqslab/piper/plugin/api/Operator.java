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

import com.atgenomix.seqslab.piper.plugin.api.executor.Executor;
import com.atgenomix.seqslab.piper.plugin.api.formatter.Formatter;
import com.atgenomix.seqslab.piper.plugin.api.loader.Loader;
import com.atgenomix.seqslab.piper.plugin.api.transformer.Transformer;
import com.atgenomix.seqslab.piper.plugin.api.writer.Writer;
import com.atgenomix.seqslab.piper.plugin.api.collector.Collector;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;

import java.io.Closeable;
import java.io.Serializable;
import java.lang.Cloneable;

/**
 * An operator that processes Spark DataFrame and produces a new DataFrame.
 * Multiple operators chained together is the operator pipeline that automates in-memory
 * data processing specific to an input or output file or tabular data in a workflow task.
 *
 * Operations available in operator pipeline are divided into localization, computation,
 * and delocalization.
 *
 * <br>Localization loads datasets from a source (such as blob storage) and optionally
 * transforms the dataset to meet the requirements of distributed task commands.
 * <br>Computation passes DataFrame partitions to task command as inputs and executes
 * the task command (such as shell script or SQL).
 * <br>Delocalization collects a file or dataset outputted from task command and saves to
 * a destination (such as blob storage).
 *
 * @see Loader
 * @see Transformer
 * @see Formatter
 * @see Executor
 * @see Collector
 * @see Writer
 */
@DeveloperApi
public interface Operator extends OperatorPipelineV3, Serializable, Closeable {

    /**
     * Get the operator name that is used to uniquely specify the operator
     * configuration of task operator pipelines.
     * @return operator name
     */
    default String getName() { return this.getClass().getSimpleName(); }

    /**
     * Get the {@link OperatorContext} containing a list of properties in the form
     * of NamedValue objects associated with this operator object.
     * Operator context can store state properties that will be passed to downstream
     * operators.
     * @return operator context
     */
    OperatorContext getOperatorContext();
}
