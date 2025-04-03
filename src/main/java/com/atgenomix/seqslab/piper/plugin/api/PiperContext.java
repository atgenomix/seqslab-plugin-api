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

import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * An object used in all operations of a workflow task execution. It represents a persistent set of properties,
 * including Spark session, pipeline configurations, and environment context.
 * A piper context object is first created when a workflow task is requested and then used to lazily
 * initialize all registered SeqsLab {@link PiperPlugin}, each of which returns its {@link PluginContext}
 * in order to later initialize its invoked pipeline {@link Operator}.
 *
 * @see PluginContext
 * @see OperatorContext
 */
public abstract class PiperContext implements Serializable {

    /**
     * An entry point for operators to work with DataFrame and Dataset.
     */
    public SparkSession spark;

    /**
     * A persistent configuration of a workflow task request.
     */
    private final Pipeline pipeline;

    /**
     * Creates a context object with the specified Spark session and pipeline configuration.
     * @param spark Spark session for operators to work with DataFrame and Dataset.
     * @param pipeline A persistent configuration of a workflow task request.
     */
    protected PiperContext(SparkSession spark, Pipeline pipeline) {
        this.spark = spark;
        this.pipeline = pipeline;
    }

    /**
     * Get the information of execution environment and configuration.
     * @return A map of NamedValue properties
     */
    public Map<String, String> getConf() {
        Map<String, String> conf = Arrays
                .stream(spark.sparkContext().getConf().getAll())
                .collect(Collectors.toMap(Tuple2::_1, Tuple2::_2));
        // TODO add WE2 backend configuration
        return conf;
    }

    /**
     * Obtain the dataset object value of an input variable identified by fully-qualified name.
     * @param fqn The fully-qualified name of an input variable, e.g. myworkflow.task.ref.
     * @return The dataset object value or null if not found.
     */
    public PiperValue getDataset(String fqn) {
        return pipeline.getDatasets().get(fqn);
    }

    /**
     * Obtain the pipeline task of an input or output variable identified by fully-qualified name.
     * @param fqn The fully-qualified name of an input or output variable, e.g. myworkflow.task.fastqR1.
     * @return The pipeline task object or null if not found.
     */
    public PipelineTask getTask(String fqn) {
        return pipeline.getTasks().get(fqn);
    }
}
