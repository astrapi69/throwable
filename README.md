# Overview

<div align="center">

[![Build Status](https://travis-ci.org/astrapi69/throw-able.svg?branch=master)](https://travis-ci.org/astrapi69/throw-able) 
[![Coverage Status](https://codecov.io/gh/astrapi69/throw-able/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/throw-able)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/throw-able.svg?style=flat)](https://github.com/astrapi69/throw-able/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/throw-able/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/throw-able)
[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/throw-able.svg)](http://www.javadoc.io/doc/de.alpharogroup/throw-able)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

</div>

Project that holds utilities for get the stacktrace as string from java throwable objects and 
can decorate checked exceptions and transform them to unchecked exceptions

If you like this project put a ⭐ and donate

# Features

- Transform stacktrace object from Throwable(Exceptions, Errors) to String object 
- Transform checked exceptions to unchecked exceptions
- Transform checked exceptions to unchecked exceptions in streams

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

So the method FileFactory#createFile decorates the method (that throws a checked IOExceptions) FileFactory#newFile 
and do not need to have a throw clause in the method signature.

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
		sources.stream().forEach(ThrowableExtensions.toRuntimeExceptionIfNeeded(file -> {
			File destinationFile = new File(destination, file.getName());
			CopyFileExtensions.copyFile(file, destinationFile, sourceEncoding, destinationEncoding,
				lastModified);
		}));
	}
    ...
}
```

So you can use the method ThrowableExtensions#toRuntimeExceptionIfNeeded for streams as you can see
in the above example. This is provided with the inteface ThrowableConsumer that is a FunctionalInterface 

## License

The source code comes under the liberal MIT License, making throw-able great for all types of applications.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of throw-able:

Than you can add the dependency to your dependencies:

	<properties>
			...
		<!-- THROW-ABLE version -->
		<throw-able.version>1.4</throw-able.version>
			...
	</properties>
			...
		<dependencies>
			...
            <!-- THROW-ABLE DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>throw-able</artifactId>
				<version>${throw-able.version}</version>
			</dependency>
			...
		</dependencies>

			
## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to your project `build.gradle` if you want to import the core functionality of throw-able:

```
ext {
			...
    throwAbleVersion = "1.4"
			...
}
dependencies {
			...
compile("de.alpharogroup:throw-able:$throwAbleVersion")
			...
}
```

## Want to Help and improve it? ###

The source code for throw-able are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/throw-able/fork](https://github.com/astrapi69/throw-able/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/throw-able/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the throw-able developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/throw-able/issues).

## Note

No animals were harmed in the making of this library.

# Donations

If you like this library, please consider a donation through paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=B37J9DZF6G9ZC" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

36JxRRDfRazLNqUV6NsywCw1q7TK38ukpC

or over ether with:

0x588Aa02De98B1Ef70afeDC3ec5290130a3E5e273

or over flattr: 
<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Fastrapi69%2Fthrow-able" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" />
</a>

## Similar projects

Here is a list of awesome similar projects:

Open Source:

 * [throwing-function](https://github.com/pivovarit/throwing-function) Checked Exceptions-enabled Java 8+ functional interfaces + adapters
 * [sneakythrow](https://github.com/rainerhahnekamp/sneakythrow) SneakyThrow is a Java library to ignore checked exceptions

## Credits

|Travis CI|
|:-:|
|![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)|
|Many thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects.|
|![sonatype repository](https://avatars1.githubusercontent.com/u/33330803?s=200&v=4)|
|Many thanks to [sonatype repository](https://oss.sonatype.org) for providing a free maven repository service for open source projects.|
|![Coverage Status](https://coveralls.io/repos/github/astrapi69/throw-able/badge.svg)|
|Many thanks to [coveralls.io](https://coveralls.io) for providing a free code coverage for open source projects.|
|![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/throw-able.svg)|
|Many thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects.|
