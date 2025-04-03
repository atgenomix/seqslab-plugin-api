# SeqsLab Kernel Plugin Framework API

SeqsLab Kernel Plugin Framework API is a Java library that provides a framework for creating and managing plugins in the SeqsLab environment. It allows developers to create custom plugins that can be easily integrated into the SeqsLab platform.

Extended from interfaces of SeqsLab Plugin API, third-party developers can create their own [Operators and Pipelines](https://docs.atgenomix.com/concepts/operator-pipelines.html) which can further registered inside SeqsLab API and used in the SeqsLab environment.

## Way to apply User-Defined Plugins
SeqsLab supports third-party developers to develop their own plugins and apply them own data processing methods on SeqsLab platform. Here is the road map:

1. Create your own plugin by implementing the interfaces of SeqsLab Plugin API.
2. Build your plugin and package it as a JAR file.
3. Encapsulate the JAR file into a Docker image with SeqsLab base image.
4. Include the plugin in Spark Conf `spark.piper.plugins`.

Note: default `spark.piper.plugins` is `com.atgenomix.seqslab.piper.plugin.atgenomix.AtgenomixPiperPlugin`.