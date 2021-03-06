/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.cors;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.http.HttpMethod;
import org.springframework.mock.web.test.MockHttpServletRequest;

/**
 * Unit tests for {@link CorsConfigurationMapping}.
 * @author Sebastien Deleuze
 */
public class CorsConfigurationMappingTests {

	private final CorsConfigurationMapping mapping = new CorsConfigurationMapping();

	@Test
	public void empty() {
		assertNull(this.mapping.getCorsConfiguration(new MockHttpServletRequest(HttpMethod.GET.name(), "/bar/test.html")));
	}

	@Test
	public void registerAndMatch() {
		CorsConfiguration config = new CorsConfiguration();
		this.mapping.registerCorsConfiguration("/bar/**", config);
		assertNull(this.mapping.getCorsConfiguration(new MockHttpServletRequest(HttpMethod.GET.name(), "/foo/test.html")));
		assertEquals(config, this.mapping.getCorsConfiguration(new MockHttpServletRequest(HttpMethod.GET.name(), "/bar/test.html")));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void unmodifiableConfigurationsMap() {
		this.mapping.getCorsConfigurations().put("/**", new CorsConfiguration());
	}

}
