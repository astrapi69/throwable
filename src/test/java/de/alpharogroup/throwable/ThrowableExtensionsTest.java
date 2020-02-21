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

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.throwable.api.ThrowableConsumer;

/**
 * The unit test class for the class {@link ThrowableExtensions}
 */
public class ThrowableExtensionsTest
{

	/**
	 * Test method for {@link ThrowableExtensions#getStackTrace(Throwable, String...)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@SuppressWarnings("null")
	@Test
	public void testGetStackTrace() throws IOException
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
			actual = ThrowableExtensions.getStackTrace(npe);
		}
		expected = "java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));

		try
		{
			nullObject.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ThrowableExtensions.getStackTrace(npe, "foo", "bar");
		}

		expected = "foo, bar java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));
	}


	/**
	 * Test method for {@link ThrowableExtensions#getStackTraceElements(Throwable, String...)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@SuppressWarnings("null")
	@Test
	public void testGetStackTraceElements() throws IOException
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
			actual = ThrowableExtensions.getStackTraceElements(npe);
		}
		expected = "class java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));

		try
		{
			nullObject.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ThrowableExtensions.getStackTraceElements(npe, "foo", "bar");
		}

		expected = "foo, bar";
		assertTrue(actual.startsWith(expected));
	}

	/**
	 * Test method for {@link ThrowableExtensions#newThrowableMessage(Throwable, String)}.
	 */
	@SuppressWarnings("null")
	@Test
	public void testNewThrowableMessage()
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
			actual = ThrowableExtensions.newThrowableMessage(npe, "NPE");
		}
		expected = "NPE [NullPointerException]: empty message";
		assertEquals(actual, expected);

		try
		{
			nullObject.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ThrowableExtensions.newThrowableMessage(npe, "Fatal");
		}
		expected = "Fatal [NullPointerException]: empty message";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link ThrowableExtensions#toRuntimeExceptionIfNeeded(ThrowableConsumer)}
	 */
	@Test
	public void testToRuntimeExceptionIfNeeded()
	{
		List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
		list.forEach(ThrowableExtensions.toRuntimeExceptionIfNeeded(i -> Thread.sleep(i)));
	}

	/**
	 * Test method for {@link ThrowableExtensions#toRuntimeExceptionIfNeeded(ThrowableConsumer)}
	 */
	@Test(expectedExceptions = { RuntimeException.class })
	public void testToRuntimeExceptionIfNeededWithThrow()
	{
		List<String> integers = Arrays.asList("44", "xyz", "145");
		integers.forEach(ThrowableExtensions
			.toRuntimeExceptionIfNeeded(str -> System.out.println(Integer.parseInt(str))));
	}

	/**
	 * Test method for {@link ThrowableExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ThrowableExtensions.class);
	}

}
