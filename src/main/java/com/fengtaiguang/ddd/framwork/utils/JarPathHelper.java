package com.fengtaiguang.ddd.framwork.utils;

import org.apache.commons.lang3.StringUtils;

public abstract class JarPathHelper {
	public static String jarPath(String fullPathString) {
		String[] lines = fullPathString.split("!");
		String line0 = lines[0];
		String line = StringUtils.stripStart(line0, "file:");
		if (line.contains(":")) {
			return StringUtils.stripStart(line, "/").trim();
		}
		return line.trim();
	}
}
