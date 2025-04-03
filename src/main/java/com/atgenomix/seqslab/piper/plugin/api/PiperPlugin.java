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

import com.atgenomix.seqslab.piper.plugin.api.collector.CollectorSupport;
import com.atgenomix.seqslab.piper.plugin.api.executor.ExecutorSupport;
import com.atgenomix.seqslab.piper.plugin.api.formatter.FormatterSupport;
import com.atgenomix.seqslab.piper.plugin.api.transformer.TransformerSupport;
import com.atgenomix.seqslab.piper.plugin.api.writer.WriterSupport;
import com.atgenomix.seqslab.piper.plugin.api.loader.LoaderSupport;
import com.atgenomix.seqslab.piper.tags.DeveloperApi;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.rules.Rule;
import org.apache.spark.sql.expressions.UserDefinedFunction;


/**
 * A plugin that can be dynamically loaded into a SeqsLab piper application.
 * Plugins can be loaded by adding the plugin's class name to the appropriate SeqsLab configuration
 * with Spark conf, e.g. --conf seqslab.piper.plugins=org.bio.WGSPipelinePlugin.
 */
@DeveloperApi
public interface PiperPlugin {

    /**
     * Initialize the plugin.
     *
     * @param context Additional context about the SeqsLab application where the plugin is running.
     * @return A plugin-specific context that is used to initialize plugin-specific operators.
     */
    default PluginContext init(PiperContext context) {
        return new PluginContext(context);
    }

    /**
     * Registration of Spark session extensions specially for resolution and optimization rules.
     */
    default Map<String, List<Rule<LogicalPlan>>> registerExtensions() {
        return new HashMap<String, List<Rule<LogicalPlan>>>() {{
            put("ResolutionRule", Collections.emptyList());
            put("OptimizerRule", Collections.emptyList());
        }};
    }

    /**
     * Register loader operators published by the plugin with SeqsLab piper system.
     *
     * @return A map of uniquely-named operator factory that will be registered into operator registry.
     * The operator factory is used to created dataset loading operators
     * when pipeline task requests.
     */
    default Map<String, LoaderSupport> registerLoaders() {
        return Collections.emptyMap();
    }

    /**
     * Register partitioning operators published by the plugin with SeqsLab piper system.
     *
     * @return A map of uniquely-named operator factory that will be registered into operator registry.
     * The operator factory is used to created dataset partitioning operators
     * when pipeline task requests.
     */
    default Map<String, TransformerSupport> registerTransformers() {
        return Collections.emptyMap();
    }

    /**
     * Register format transformation operators published by the plugin with SeqsLab piper system.
     *
     * @return A map of uniquely-named operator factory that will be registered into operator registry.
     * The operator factory is used to created dataset format transformation
     * operators when pipeline task requests.
     */
    default Map<String, FormatterSupport> registerFormatters() {
        return Collections.emptyMap();
    }

    /**
     * Register executing operators published by the plugin with SeqsLab piper system.
     *
     * @return A map of uniquely-named operator factory that will be registered into operator registry.
     * The operator factory is used to created operators that prepares datasets for
     * task command execution when pipeline task requests.
     */
    default Map<String, ExecutorSupport> registerExecutors() {
        return Collections.emptyMap();
    }

    /**
     * Register dataset collecting operators published by the plugin with SeqsLab piper system.
     *
     * @return A map of uniquely-named operator factory that will be registered into operator registry.
     * The operator factory is used to created operators that collects output files or datasets
     * after task command execution is complete when pipeline task requests.
     */
    default Map<String, CollectorSupport> registerCollectors() {
        return Collections.emptyMap();
    }

    /**
     * Register dataset saving operators published by the plugin with SeqsLab piper system.
     *
     * @return A map of uniquely-named operator factory that will be registered into operator registry.
     * The operator factory is used to created operators that save task command output
     * files or collected datasets to specific storages when pipeline task requests.
     */
    default Map<String, WriterSupport> registerWriters() {
        return Collections.emptyMap();
    }

    /**
     * Register UserDefinedFunction (UDF) published by the plugin with SeqsLab piper system.
     *
     * @return A map of uniquely-named udf that will be registered into sparkSession.
     */
    default Map<String, UserDefinedFunction> registerUDFs() {
        return Collections.emptyMap();
    }

    /**
     * Register UDAF as UserDefinedFunction published by the plugin using functions.udaf()
     *
     * @return A map of uniquely-named user defined function that will be registered into sparkSession.
     */
    default Map<String, UserDefinedFunction> registerUDAFs() {
        return Collections.emptyMap();
    }
}
