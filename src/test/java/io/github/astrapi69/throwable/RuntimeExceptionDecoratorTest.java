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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import io.github.astrapi69.file.delete.DeleteFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.throwable.api.RuntimeExceptionDecoratable;
import io.github.astrapi69.throwable.api.ThrowableConsumer;
import io.github.astrapi69.throwable.api.ThrowableNoArgumentConsumer;

/**
 * The unit test class for the class {@link RuntimeExceptionDecorator}
 */
public class RuntimeExceptionDecoratorTest
{

	/**
	 * Copies(serialize) the given object to a byte array
	 *
	 * @param <T>
	 *            the generic type of the given object
	 * @param object
	 *            The Object to convert into a byte array
	 * @return The byte array from the Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T extends Serializable> byte[] toByteArray(final T object) throws IOException
	{
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream))
		{
			objectOutputStream.writeObject(object);
			return byteArrayOutputStream.toByteArray();
		}
	}

	/**
	 * Test method for {@link RuntimeExceptionDecorator#decorate(RuntimeExceptionDecoratable)}
	 */
	@Test
	public void testDecorateWithNullValue()
	{
		Object nullObject;
		String actual;
		String expected;

		nullObject = null;
		RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
			() -> RuntimeExceptionDecorator.decorate(() -> nullObject.getClass()));
		expected = "java.lang.NullPointerException: Cannot invoke \"Object.getClass()\" because \"nullObject\" is null";
		actual = runtimeException.getMessage();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link RuntimeExceptionDecorator#decorate(ThrowableNoArgumentConsumer)}
	 */
	@Test
	public void testDecorateWithVoid()
	{
		File file = null;
		new File(PathFinder.getSrcTestResourcesDir(), "todelete.txt");

		String actual;
		String expected;

		RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
			() -> RuntimeExceptionDecorator.decorate(() -> DeleteFileExtensions.delete(file)));
		expected = "java.lang.NullPointerException";
		actual = runtimeException.getMessage();
		assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link RuntimeExceptionDecorator#decorate(ThrowableNoArgumentConsumer)}
	 */
	@Test
	public void testDecorate()
	{
		Boolean actual;

		final File file = new File(PathFinder.getSrcTestResourcesDir(), "todelete.txt");

		actual = RuntimeExceptionDecorator.decorate(() -> DeleteFileExtensions.delete(file));

		assertNotNull(actual);
	}

	/**
	 * Test method for {@link RuntimeExceptionDecorator#decorate(ThrowableConsumer)}
	 */
	@Test
	public void testToRuntimeExceptionIfNeeded()
	{
		String value;

		List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
		list.forEach(RuntimeExceptionDecorator.decorate(i -> Thread.sleep(i)));

		value = "foo";
		byte[] result = RuntimeExceptionDecorator.decorate(() -> toByteArray(value));
		assertNotNull(result);

	}

	/**
	 * Test method for {@link RuntimeExceptionDecorator#decorate(ThrowableConsumer)}
	 */
	@Test
	public void testDecorateWithoutReturnValue()
	{
		List<String> integers = Arrays.asList("44", "xyz", "145");

		RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
			() -> integers.forEach(RuntimeExceptionDecorator.decorate(str -> {
				System.out.println(Integer.parseInt(str));
			})));
	}

	/**
	 * Test method for {@link RuntimeExceptionDecorator}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RuntimeExceptionDecorator.class);
	}
}
