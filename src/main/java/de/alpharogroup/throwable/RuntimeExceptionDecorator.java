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

import de.alpharogroup.throwable.api.RuntimeExceptionDecoratable;

import java.io.IOException;

/**
 * The class {@link RuntimeExceptionDecorator}
 *
 * @author Asterios Raptis
 * @version 1.0
 */
public class RuntimeExceptionDecorator
{

	/**
	 * Gets the stacktrace as a {@link String} object. <br>
	 *
	 * @param decoratable       the function object to decorate
	 * @return the generic type of the return type of the decorated function or throws
	 * a {@link RuntimeException} that decorates the thrown exception of the origin function
	 */
	public static <T> T decorate(RuntimeExceptionDecoratable<T> decoratable)
	{
		try
		{
			return decoratable.execute();
		}
		catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}

}
