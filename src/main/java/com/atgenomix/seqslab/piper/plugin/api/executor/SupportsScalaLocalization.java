package com.atgenomix.seqslab.piper.plugin.api.executor;

import com.atgenomix.seqslab.piper.tags.DeveloperApi;
import com.atgenomix.seqslab.piper.tags.FeatureAfterCall;
import com.atgenomix.seqslab.piper.tags.FeatureBeforeCall;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.api.java.UDF1;

/**
 * A mix-in interface for {@link Executor}. Executors can implement this interface to support DataFrame preprocessing
 * for Scala command execution.
 *
 * Executors implementing this mix-in interface is a user-defined function (UDF) that takes a DataFrame as an input
 * parameter and returns a new DataFrame.
 * This feature is invoked before and after applying Executor's call function.
 */
@DeveloperApi
@FeatureBeforeCall
@FeatureAfterCall
public interface SupportsScalaLocalization extends Executor, UDF1<Dataset<Row>, Dataset<Row>> {
}
