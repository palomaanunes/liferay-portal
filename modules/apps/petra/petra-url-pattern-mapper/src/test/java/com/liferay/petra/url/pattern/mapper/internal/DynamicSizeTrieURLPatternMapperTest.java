/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.petra.url.pattern.mapper.internal;

import com.liferay.petra.url.pattern.mapper.URLPatternMapper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Arthur Chan
 */
public class DynamicSizeTrieURLPatternMapperTest
	extends SimpleURLPatternMapperTest {

	@Test
	public void testConstructor() {
		Map<String, String> map = new HashMap<>();

		for (int i = 0; i < 1024; i++) {
			map.put("*.key" + i, "value" + i);
		}

		URLPatternMapper<String> urlPatternMapper = createURLPatternMapper(map);

		for (int i = 0; i < 1024; i++) {
			Assert.assertEquals(
				"value" + i, urlPatternMapper.getValue("*.key" + i));
		}
	}

	@Override
	protected URLPatternMapper<String> createURLPatternMapper(
		Map<String, String> values) {

		return new DynamicSizeTrieURLPatternMapper<>(values);
	}

}