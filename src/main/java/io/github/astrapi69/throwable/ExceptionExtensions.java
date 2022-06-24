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
package io.github.astrapi69.throwable;

/**
 * The class {@link ExceptionExtensions} provides methods for convert exceptions to readable string
 * objects.
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public final class ExceptionExtensions
{
	private ExceptionExtensions()
	{
	}

	/**
	 * Gets the stacktrace as a {@link String} object. <br>
	 *
	 * @param exception
	 *            the {@link Exception} object
	 * @param additionalInfos
	 *            the additional infos
	 * @return the stacktrace as a {@link String} object
	 */
	public static String getStackTrace(final Exception exception, String... additionalInfos)
	{
		if (exception == null)
		{
			return "";
		}
		return ThrowableExtensions.getStackTrace(exception, additionalInfos);
	}

	/**
	 * Gets the stack trace elements from the given Throwable and returns a {@link String} object
	 * from it.
	 *
	 * @param exception
	 *            the exception
	 * @param additionalInfos
	 *            the additional information to the given exception
	 * @return the stack trace elements
	 */
	public static String getStackTraceElements(Exception exception, String... additionalInfos)
	{
		if (exception == null)
		{
			return "";
		}
		return ThrowableExtensions.getStackTraceElements(exception, additionalInfos);
	}

}
