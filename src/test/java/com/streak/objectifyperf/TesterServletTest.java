/**
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.streak.objectifyperf;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TesterServletTest {

  private TesterServlet testerServlet;

  private final LocalServiceTestHelper helper = new LocalServiceTestHelper();

  @Before
  public void setupGuestBookServlet() {
    helper.setUp();
    testerServlet = new TesterServlet();
  }

  @After
  public void tearDownHelper() {
    helper.tearDown();
  }

  @Test
  public void testDoGet() throws IOException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    StringWriter stringWriter = new StringWriter();

    when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

    testerServlet.doGet(request, response);

    assertEquals("Hello world!\n", stringWriter.toString());
  }

}
