# SeqsLab Kernel Plugin Framework API

SeqsLab Kernel Plugin Framework API is a Java library that provides a framework for creating and managing plugins in the SeqsLab environment. It allows developers to create custom plugins that can be easily integrated into the SeqsLab platform.

Extended from interfaces of SeqsLab Plugin API, third-party developers can create their own [Operators and Pipelines](https://docs.atgenomix.com/concepts/operator-pipelines.html) which can further registered inside SeqsLab API and used in the SeqsLab environment.

## Way to apply User-Defined Plugins
SeqsLab supports third-party developers to develop their own plugins and apply them own data processing methods on SeqsLab platform. Here is the road map:

### 1. Create your own plugin:
By implementing the interfaces of SeqsLab Plugin API, you need to at least implement a customized class inheriting PiperPlugin class
### 2. Package your plugin into a JAR file:
### 3. Build a Docker image including the JAR file based on SeqsLab image:
Create your Dockerfile begins with one of image from https://github.com/orgs/atgenomix/packages/container/package/runtime%2Fbase such as 
```
ARG base_image=ghcr.io/atgenomix/runtime/base:1.5_20.04

FROM ${base_image}
ARG base_image

# metadata
LABEL \
    org.opencontainers.image.authors="Yu-Ting-Lin <yuting.lin@atgenomix.com>" \
    org.opencontainers.image.description="For Demo Purposes" \
    org.opencontainers.image.base.name="${base_image}"
LABEL maintainer="Atgenomix Team <info@atgenomix.com>"
```
Then copy your compiled JAR file under /home/spark-current/jars folder as 
```
wget -O your-plugin.jar https://... /home/spark-current/jars/
```
### 4. Include the plugin in Spark Conf `spark.piper.plugins`:
When specifying Runtime options to provision a cluster, change default Spark conf from 
```
spark.piper.plugins com.atgenomix.seqslab.piper.plugin.atgenomix.AtgenomixPiperPlugin
```
to
```
spark.piper.plugins com.atgenomix.seqslab.piper.plugin.atgenomix.AtgenomixPiperPlugin,com.yourcompany.plugin.YourPlugin
```
Users can append their own plugin with comma separator.