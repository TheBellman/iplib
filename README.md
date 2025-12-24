# iplib

This is a very small libary that is to be used for extracting IP ranges for services from a list provided by AWS.

## Usage

This is a simple JAR based library. To use it, include the dependency

```xml
<dependency>
    <groupId>net.parttimepolymath</groupId>
    <artifactId>iplib</artifactId>
    <version>1.1.0</version>
</dependency>
```

Then construct an instance:

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
This was built with Java 21 and Maven 3.9.

It is assumed that Maven is in your execution path.

### settings.xml
The package is hosted in the GitHub maven repository, so you need to set up your settings.xml to be able to pull
the dependency from there.

You can find out more at [Working with the Apache Maven registry](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-to-github-packages)

In essence though, you need to create a personal GitHub token as described at ....

Then you need to setup your `~.m2/settings.xml` something like this so that Maven can find and access the
repository that contains the dependency.

```xml
<?xml version="1.0" encoding="utf-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd">
  <!-- https://maven.apache.org/settings.html -->
  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo.maven.apache.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/TheBellman/ldutils</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>YOUR_HANDLE</username>
      <password>YOUR_TOKEN</password>
    </server>
  </servers>

</settings>
```

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
[INFO] Building jar: /Users/robert/Projects/Java/iplib/target/iplib-1.1.0.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  7.653 s
[INFO] Finished at: 2021-02-27T17:55:15Z
[INFO] ------------------------------------------------------------------------
```

The built JAR will be available in `target`.

JavaDoc can be generated usi `mvn clean install` to create the `target/iplib-1.1.0.javadoc.jar`

## License

Copyright 2025 Little Dog Digital

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.

You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.