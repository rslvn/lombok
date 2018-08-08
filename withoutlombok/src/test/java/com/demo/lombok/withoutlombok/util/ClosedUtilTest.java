/**
 * 
 */
package com.demo.lombok.withoutlombok.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author resulav
 *
 */
public class ClosedUtilTest {

	private static final String filename = "test_withoutlombok.txt";

	@AfterClass
	public static void teardown() throws IOException {
		try {
			Files.delete(Paths.get(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConstructor(){
		ClosedUtil closedUtil = new ClosedUtil();
		Assert.assertNotNull("closedUtil can not be null",closedUtil);
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
			e.printStackTrace();
		}

		ClosedUtil.write(filename, texts);
	}

	@Test(expected = IOException.class)
	public void testClosedFileNotClosed() throws IOException {
		String[] textsNew = { "dummy line5", "dummy line6" };
		OutputStream out = ClosedUtil.write(filename, textsNew);
		out.write("testClosedFileNotClosed".getBytes());
	}

}
