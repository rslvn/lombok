package com.demo.lombok.withoutlombok.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.common.base.Preconditions;

/**
 * Created by resulav on 07.08.2018.
 * 
 * 
 */
public class ClosedUtil {

	protected ClosedUtil() {
		// for sonar
	}

	/**
	 * @param filename
	 * @param texts
	 * @return
	 * @throws IOException
	 */
	public static OutputStream write(String filename, String... texts) throws IOException {
		try (OutputStream out = new FileOutputStream(filename, true)) {
			// this line will used the case if an exception occurres after opening a file
			Preconditions.checkArgument(texts.length > 0, "texts can not be empty");
			for (String text : texts) {
				out.write(text.getBytes());
			}
			return out;
		}
	}
}
