# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/throwable/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/throwable/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/throwable/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/throwable)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/throwable.svg?style=flat)](https://github.com/astrapi69/throwable/issues)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/throwable)](https://central.sonatype.com/artifact/io.github.astrapi69/throwable)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/throwable.svg)](http://www.javadoc.io/doc/io.github.astrapi69/throwable)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/throwable?branch=develop)](https://hitsofcode.com/github/astrapi69/throwable/view?branch=develop)

</div>

Project that holds utilities for get the stacktrace as string from java throwable objects and can
decorate checked exceptions and transform them to unchecked exceptions

If you like this project put a ⭐ and donate

> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
<a class="github-button" href="https://github.com/astrapi69/throwable" data-icon="octicon-star" aria-label="Star astrapi69/throwable on GitHub">Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

# Features

- Transform stacktrace object from Throwable(Exceptions, Errors) to String object
- Transform checked exceptions to unchecked exceptions
- Transform checked exceptions to unchecked exceptions in streams

## License

The source code comes under the liberal MIT License, making throwable great for all types of
applications.

## gradle dependency

Replace the variable ${latestVersion} with the current latest version: [![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/throwable)](https://central.sonatype.com/artifact/io.github.astrapi69/throwable)

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of throwable:

define version in file gradle.properties

```
throwableVersion=${latestVersion}
```

or in build.gradle ext area

```
    throwableVersion = "${latestVersion}"
```

and then add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:throwable:$throwableVersion")
```

# with new libs.versions.toml file

If you use the new libs.versions.toml file for new automatic catalog versions update

```
[versions]
throwable-version=${latestVersion}

[libraries]
throwable = { module = "io.github.astrapi69:throwable", version.ref = "throwable-version" }
```
then add the dependency to the dependencies area

```
    implementation libs.throwable
```

## Maven dependency

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of throwable:

Than you can add the dependency to your dependencies:

    <properties>
            ...
        <!-- THROW-ABLE version -->
        <throwable.version>${latestVersion}</throwable.version>
            ...
    </properties>
            ...
        <dependencies>
            ...
            <!-- THROW-ABLE DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>throwable</artifactId>
                <version>${throwable.version}</version>
            </dependency>
            ...
        </dependencies>

# Usage

You can decorate a method that throws a checked exceptions with the class RuntimeExceptionDecorator

```
public final class FileFactory
{
    public static FileCreationState newFile(final File file) throws IOException
    {
        FileCreationState fileCreationState = FileCreationState.ALREADY_EXISTS;
        if (!file.exists())
        {
            fileCreationState = FileCreationState.FAILED;
            newParentDirectories(file);
            if (file.createNewFile())
            {
                fileCreationState = FileCreationState.CREATED;
            }
        }
        return fileCreationState;
    }

    public static FileCreationState createFile(final File file)
    {
        return RuntimeExceptionDecorator.decorate(()-> newFile(file));
    }
}
```

The above example shows the method FileFactory#newFile that throws a checked IOExceptions. The
method FileFactory#createFile decorates the method FileFactory#newFile with the method decorate of
the utility class RuntimeExceptionDecorator and do not have to declare a throw clause in the method
signature. Note: the method FileFactory#createFile returns a FileCreationState object

```
public final class CopyFileExtensions
{
    public static void copyFiles(final List<File> sources, final File destination,
        final Charset sourceEncoding, final Charset destinationEncoding, final boolean lastModified)
    {
        if (!destination.exists())
        {
            FileFactory.newDirectory(destination);
        }
        sources.stream().forEach(RuntimeExceptionDecorator.decorate(file -> {
            File destinationFile = new File(destination, file.getName());
            CopyFileExtensions.copyFile(file, destinationFile, sourceEncoding, destinationEncoding,
                lastModified);
        }));
    }
    ...
}
```

The method RuntimeExceptionDecorator#decorate is overloaded, so you can use it also for streams as
you can see in the above example. This is provided with the inteface ThrowableConsumer that is a
FunctionalInterface.

## Want to Help and improve it? ###

The source code for throwable are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/throwable/fork](https://github.com/astrapi69/throwable/fork)

To share your
changes, [submit a pull request](https://github.com/astrapi69/throwable/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the throwable developers with your questions, concerns, comments, bug
reports, or feature requests.

- Feature requests, questions and bug reports can be reported at
  the [issues page](https://github.com/astrapi69/throwable/issues).

## Note

No animals were harmed in the making of this library.

## Support this project

If throwable is useful to you, a star on [GitHub](https://github.com/astrapi69/throwable) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Similar projects

Here is a list of awesome similar projects:

Open Source:

* [throwing-function](https://github.com/pivovarit/throwing-function) Checked Exceptions-enabled
  Java 8+ functional interfaces + adapters
* [sneakythrow](https://github.com/rainerhahnekamp/sneakythrow) SneakyThrow is a Java library to
  ignore checked exceptions

## Credits

|**Maven Central**|
|     :---:      |
|[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/throwable?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.astrapi69/throwable)|
|Special thanks to [sonatype repository](https://www.sonatype.com) for providing a free maven repository service for open source projects|
|     <img width=1000/>     |

|**codecov.io**|
|     :---:      |
|[![Coverage Status](https://codecov.io/gh/astrapi69/throwable/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/throwable)|
|Special thanks to [codecov.io](https://codecov.io) for providing a free code coverage for open source projects|
|     <img width=1000/>     |

|**javadoc.io**|
|     :---:      |
|[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/throwable.svg)](http://www.javadoc.io/doc/io.github.astrapi69/throwable)|
|Special thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects|
|     <img width=1000/>     |
