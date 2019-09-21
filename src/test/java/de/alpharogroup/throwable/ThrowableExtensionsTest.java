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

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link ThrowableExtensions}
 */
public class ThrowableExtensionsTest
{

	/**
	 * Test method for {@link ThrowableExtensions#getStackTrace(Throwable, String...)}
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	@Test
	public void testGetStackTrace() throws IOException
	{
		String expected;
		String actual;
		actual = null;
		try
		{
			final Object objNull = null;
			objNull.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ThrowableExtensions.getStackTrace(npe);
		}
		expected = "java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));

		try
		{
			final BeanTester beanTester = new BeanTester();
			beanTester.testBean(ThrowableExtensions.class);
		}
		catch (final Exception e)
		{
			actual = ThrowableExtensions.getStackTrace(e);
		}

		expected = "org.meanbean.test.BeanTestException";
		assertTrue(actual.startsWith(expected));

		try
		{
			final BeanTester beanTester = new BeanTester();
			beanTester.testBean(ThrowableExtensions.class);
		}
		catch (final Exception e)
		{
			actual = ThrowableExtensions.getStackTrace(e, "foo", "bar");
		}

		expected = "foo, bar org.meanbean.test.BeanTestException";
		assertTrue(actual.startsWith(expected));
	}

	/**
	 * Test method for {@link ThrowableExtensions#getStackTraceElements(Throwable, String...)}
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	@Test
	public void testGetStackTraceElements() throws IOException
	{
		String expected;
		String actual;
		actual = null;
		try
		{
			final Object objNull = null;
			objNull.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ThrowableExtensions.getStackTraceElements(npe);
		}
		expected = "class java.lang.NullPointerException";
		assertTrue(actual.startsWith(expected));

		try
		{
			final BeanTester beanTester = new BeanTester();
			beanTester.testBean(ThrowableExtensions.class);
		}
		catch (final Exception e)
		{
			actual = ThrowableExtensions.getStackTraceElements(e);
		}

		expected = "class org.meanbean.test.BeanTestException";
		assertTrue(actual.startsWith(expected));

		try
		{
			final BeanTester beanTester = new BeanTester();
			beanTester.testBean(ThrowableExtensions.class);
		}
		catch (final Exception e)
		{
			actual = ThrowableExtensions.getStackTraceElements(e, "foo", "bar");
		}

		expected = "foo, bar";
		assertTrue(actual.startsWith(expected));
	}

	/**
	 * Test method for {@link ThrowableExtensions#newThrowableMessage(Throwable, String)}
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	@Test
	public void testNewThrowableMessage()
	{

		String expected;
		String actual;
		actual = null;
		try
		{
			final Object objNull = null;
			objNull.getClass();
		}
		catch (final NullPointerException npe)
		{
			actual = ThrowableExtensions.newThrowableMessage(npe, "NPE");
		}
		expected = "NPE [NullPointerException]: empty message";
		assertEquals(actual, expected);

		try
		{
			final BeanTester beanTester = new BeanTester();
			beanTester.testBean(ThrowableExtensions.class);
		}
		catch (final Exception e)
		{
			actual = ThrowableExtensions.newThrowableMessage(e, "Fatal");
		}

		expected = "Fatal [BeanTestException]: Cannot test bean";
		assertTrue(actual.startsWith(expected));

	}

	/**
	 * Test method for {@link ThrowableExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ThrowableExtensions.class);
	}

}
