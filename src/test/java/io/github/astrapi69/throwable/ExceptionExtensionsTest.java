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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

/**
 * The unit test class for the class {@link ExceptionExtensions}
 */
public class ExceptionExtensionsTest
{
	/**
	 * Test method for {@link ExceptionExtensions#getStackTraceElements(Exception, String...)}
	 */
	@Test
	public void testGetStackTraceElementsWithNullValue()
	{
		String expected;
		String actual;
		Exception exception;

		exception = null;
		actual = ExceptionExtensions.getStackTraceElements(exception);
		expected = "";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ExceptionExtensions#getStackTrace(Exception, String...)}
	 */
	@Test
	public void testGetStackTraceWithNullValue()
	{
		String expected;
		String actual;
		Exception exception;

		exception = null;
		actual = ExceptionExtensions.getStackTrace(exception);
		expected = "";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ExceptionExtensions#getStackTrace(Exception, String...)}
	 */
	@Test
	public void testGetStackTrace()
	{
		String expected;
		String actual;
		actual = null;
		Object nullObject;

		nullObject = null;
		try
		{
			nullObject.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ExceptionExtensions.getStackTrace(npe);
		}
		expected = "java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));

		try
		{
			nullObject.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ExceptionExtensions.getStackTrace(npe, "foo", "bar");
		}

		expected = "foo, bar java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));
	}

	/**
	 * Test method for {@link ExceptionExtensions#getStackTraceElements(Exception, String...)}
	 */
	@Test
	public void testGetStackTraceElements()
	{
		String expected;
		String actual;
		actual = null;
		Object nullObject;

		nullObject = null;
		try
		{
			nullObject.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ExceptionExtensions.getStackTraceElements(npe);
		}
		expected = "class java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));

		try
		{
			nullObject.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ExceptionExtensions.getStackTraceElements(npe, "foo", "bar");
		}

		expected = "foo, bar";
		assertTrue(actual.startsWith(expected));
	}

	/**
	 * Test method for {@link ExceptionExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ExceptionExtensions.class);
	}

}
