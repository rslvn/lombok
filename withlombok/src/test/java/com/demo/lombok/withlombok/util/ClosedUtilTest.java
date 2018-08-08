/**
 * 
 */
package com.demo.lombok.withlombok.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author resulav
 *
 */
@Slf4j
public class ClosedUtilTest {

	private static final String filename = "test_withlombok.txt";

	@SneakyThrows
	@AfterClass
	public static void teardown() {
		Files.delete(Paths.get(filename));
	}

	@Test
	public void testConstructor() {
		ClosedUtil closedUtil = new ClosedUtil();
		Assert.assertNotNull("closedUtil can not be null", closedUtil);
	}

	@Test
	public void testClosedFile() throws IOException {
		String[] texts = { "dummy line1", "dummy line2" };
		String[] textsNew = { "dummy line3", "dummy line4" };

		ClosedUtil.write(filename, texts);
		ClosedUtil.write(filename, textsNew);
	}

	@Test
	public void testClosedFileEmptyTexts() throws IOException {
		String[] texts = { "dummy line3", "dummy line4" };
		try {
			ClosedUtil.write(filename);
		} catch (Exception e) {
			log.error("", e);
		}

		ClosedUtil.write(filename, texts);
	}

	@Test(expected = IOException.class)
	public void testClosedFileNotClosed() throws IOException {
		String[] textsNew = { "dummy line5", "dummy line6" };
		OutputStream out = ClosedUtil.write(filename, textsNew);
		out.write("test".getBytes());
	}

	@Test
	public void testClosable() throws IOException {
		@Cleanup
		DummyClosable dummyClosable = new DummyClosable();
	}

	@Test(expected = RuntimeException.class)
	public void testClosableException() throws IOException {
		@Cleanup
		DummyClosable dummyClosable = new DummyClosable();
		throw new RuntimeException("Dummy exception");
	}

	private class DummyClosable implements Closeable {
		@Override
		public void close() throws IOException {
			log.info("{} closed", this.getClass().getSimpleName());
		}
	}
}
