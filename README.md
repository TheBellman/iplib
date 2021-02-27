# iplib

This is a very small libary that is to be used for extracting IP ranges for services from a list provided by AWS.

## Usage

To use the library, include it as a dependency, then construct an instance:

```
IPRange instance = new IPRange();
```

This will fetch the required data from AWS and prepare it for querying. This data is retained for a certain number of seconds before it will be refetched.

Alternately, the TTL can be specified in seconds:

```
IPRange instance = new IPRange(300);
```

Once you have an instance, there are 5 "get" operations that should be self-explanatory:

  1. `List<String> getServices()`
  1. `List<String> getRegions()`
  1. `List<String> getPrefixes(boolean ipv6)`
  1. `List<String> getPrefixes(boolean ipv6, String region)`
  1. `List<String> getPrefixes(boolean ipv6, String region, String service)`

In each case, setting `ipv6` to true will return the IPV6 cidr blocks instead of the IPV4 blocks.

## Prerequisites
This was built with Java 12.0.2 and Maven 3.6.3.

It is assumed that Maven is in your execution path.

## Test and Build

Build and test using Maven:

```
$ mvn clean package
.
.
.
[INFO] Results:
[INFO] 
[INFO] Tests run: 50, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ iplib ---
[INFO] Building jar: /Users/robert/Projects/Java/iplib/target/iplib-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.653 s
[INFO] Finished at: 2021-02-27T17:55:15Z
[INFO] ------------------------------------------------------------------------
```

The built JAR will be available in `target`.

JavaDoc can be generated using `mvn javadoc:javadoc`, which will create it in HTML format under `target/site/apidocs`

Alternately, use `mvn clean install` to additionally create the `iplib-1.0-SNAPSHOT-javadoc.jar`