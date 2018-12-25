/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.throwable;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ThrowableExtensions}
 *
 * @author Asterios Raptis
 * @version 1.0
 */
@UtilityClass
public final class ThrowableExtensions
{

	/**
	 * Gets the stacktrace as a {@link String} object. <br>
	 * Note: can throw a {@link IOException} decorated in a {@link RuntimeException}
	 *
	 * @param throwable
	 *            the {@link Throwable} object
	 * @return the stacktrace as a {@link String} object
	 */
	@SneakyThrows public static String getStackTrace(final @NonNull Throwable throwable, String... additionalInfo)
	{
		StringBuilder stacktrace = getAdditionalInfo(additionalInfo, Arrays.stream(additionalInfo));
		try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw))
		{
			throwable.printStackTrace(pw);
			stacktrace.append(sw.toString());
		}
		return stacktrace.toString();
	}

	/**
	 * Gets the stack trace elements from the given Throwable and returns a {@link String} object
	 * from it.
	 *
	 * @param throwable
	 *            the throwable
	 * @param additionalInfo
	 *            the additional information to the given throwable
	 * @return the stack trace elements
	 */
	@SneakyThrows public static String getStackTraceElements(@NonNull Throwable throwable, String... additionalInfo)
	{
		StringBuilder stacktrace = getAdditionalInfo(additionalInfo, Arrays.stream(additionalInfo));
		try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw))
		{
			pw.println(throwable.getClass().toString());
			while (throwable != null)
			{
				pw.println(throwable);
				final StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				for (final StackTraceElement stackTraceElement : stackTraceElements)
				{
					pw.println("\tat " + stackTraceElement);
				}

				throwable = throwable.getCause();
				if (throwable != null)
				{
					pw.println("Caused by:\r\n");
				}
			}
			stacktrace.append(sw.toString());
		}
		return stacktrace.toString();
	}

	private static StringBuilder getAdditionalInfo(String[] additionalInfo, Stream<String> stream)
	{
		StringBuilder stacktrace = new StringBuilder();
		if (additionalInfo != null && 0 < additionalInfo.length)
		{
			stacktrace.append(stream.map(Object::toString).collect(Collectors.joining(", ")));
			stacktrace.append(" ");
		}
		return stacktrace;
	}

}
