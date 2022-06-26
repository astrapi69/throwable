# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/throwable/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/throwable/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/throwable/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/throwable)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/throwable.svg?style=flat)](https://github.com/astrapi69/throwable/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/throwable/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/throwable)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/throwable.svg)](http://www.javadoc.io/doc/io.github.astrapi69/throwable)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/throwable?branch=develop)](https://hitsofcode.com/github/astrapi69/throwable/view?branch=develop)
[![Lines Of Code](https://tokei.rs/b1/github/astrapi69/throwable)](https://github.com/astrapi69/throwable)

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

## Maven dependency

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of throwable:

Than you can add the dependency to your dependencies:

    <properties>
            ...
        <!-- THROW-ABLE version -->
        <throwable.version>2.3</throwable.version>
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

## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of throwable:

define version in file gradle.properties

```
throwAbleVersion=2.3
```

or in build.gradle ext area

```
    throwAbleVersion = "2.3"
```

and then add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:throwable:$throwAbleVersion")
```

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

# Donations

This project is kept as an open source product and relies on contributions to remain being
developed. If you like this library, please consider a donation

over paypal: <br><br>
<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MJ7V43GU2H386" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" style="border: none" />
</a>
<br><br>
or over bitcoin(BTC) with this address:

bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy.png"
alt="Donation Bitcoin Wallet" width="250"/>

or over FIO with this address:

FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop.png"
alt="Donation FIO Wallet" width="250"/>

or over Ethereum(ETH) with:

0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D.png"
alt="Donation Ethereum Wallet" width="250"/>

or over Ethereum Classic(ETC) with:

0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD.png"
alt="Donation Ethereum Classic Wallet" width="250"/>

or over Dogecoin(DOGE) with:

D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1.png"
alt="Donation Dogecoin Wallet" width="250"/>

or over Monero(XMR) with:

49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw.png"
alt="Donation Monero Wallet" width="250"/>

or over flattr:

<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Fastrapi69%2Fthrowable" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" style="border: none" />
</a>

## Similar projects

Here is a list of awesome similar projects:

Open Source:

* [throwing-function](https://github.com/pivovarit/throwing-function) Checked Exceptions-enabled
  Java 8+ functional interfaces + adapters
* [sneakythrow](https://github.com/rainerhahnekamp/sneakythrow) SneakyThrow is a Java library to
  ignore checked exceptions

## Credits

|**Nexus Sonatype repositories**|
|     :---:      |
|[![sonatype repository](https://img.shields.io/nexus/r/https/oss.sonatype.org/io.github.astrapi69/throwable.svg?style=for-the-badge)](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~throwable~~~)|
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
