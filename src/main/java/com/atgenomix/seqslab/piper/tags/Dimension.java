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

package com.atgenomix.seqslab.piper.tags;

import java.lang.annotation.*;

/**
 * Indicates the computing resource requirements, types of workload and
 * pipeline structures that an operator supports. This provides a runtime
 * configuration hint for SeqsLab to execute the target operators.
 *
 * Specifies multiple dimensions if the operator supports multiple different workloads.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(Dimensions.class)
public @interface Dimension {

    /**
     * List of supported workloads, e.g. "Whole Genome Sequencing Alignment".
     */
    String[] workloads();

    /**
     * List of Spark configurations specific to the operator.
     */
    String[] configurations() default {};

    /**
     * List of program arguments specific to the operator.
     */
    String[] arguments() default {};

    /**
     * Typical number of dataframe partitions to scale the target workloads.
     */
    int parallelizationFactor();

    /**
     * Indicates the operator implementation can run on GPU and be executed
     * with the RAPIDS Accelerator.
     */
    boolean runOnGPU() default false;

    /**
     * Minimal required memory in GB per CPU core for the target workloads.
     */
    double minMemoryPerCore();
}
