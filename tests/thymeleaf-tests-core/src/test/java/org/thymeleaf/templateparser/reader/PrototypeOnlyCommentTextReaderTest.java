/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2025 Thymeleaf (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package org.thymeleaf.templateparser.reader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public final class PrototypeOnlyCommentTextReaderTest {


    @Test
    public void test01() throws Exception {

        final String[] allMessages = computeAllPrototypeOnlyMessages();

        for (int i = 0; i < allMessages.length; i++) {
            testMessage(allMessages[i], computePrototypeOnlyEquivalent(allMessages[i]));
        }

    }



    @Test
    public void test02() throws Exception {

        testMessage("/* hello */", "/* hello */");
        testMessage("/* /*[[[ hello +]*/ */", "/* /*[[[ hello +]*/ */");
        testMessage("/* /*[[[ hello +]*/ +]]]*///", "/* /*[[[ hello +]*/ +]]]*///");
        testMessage("/* /*[[[ hello +]*/ +]]]*/// */", "/* /*[[[ hello +]*/ +]]]*/// */");
        testMessage("/* /*[+ hello +]*/ +]]]*/// */", "/*  hello  +]]]*/// */");
        testMessage("/* /*[+ hello +]*/ +]]]*/// /*[[[ */", "/*  hello  +]]]*/// /*[[[ */");
        testMessage("/* /*[+ hello +]*/ +]]]*/// /*[[[ +]]]*///*/", "/*  hello  +]]]*/// /*[[[ +]]]*///*/");
        testMessage("hello", "hello");
        testMessage("/*[[[ hello +]*/", "/*[[[ hello +]*/");
        testMessage("/*[[[ hello +]*/ a+]]]*///a", "/*[[[ hello +]*/ a+]]]*///a");
        testMessage("/*[[[ hello +]*/ +]]]*///", "/*[[[ hello +]*/ +]]]*///");
        testMessage("/*[+ hello +]*/", " hello ");
        testMessage("/*[+ hello +]*/ +]]]*///", " hello  +]]]*///");
        testMessage("/*[+ hello +]*/ aa+]]]*///bb", " hello  aa+]]]*///bb");
        testMessage("/*[+hello+]*/", "hello");
        testMessage("/*[+hello+]*/ +]]]*/// aa", "hello +]]]*/// aa");
        testMessage("/*[+hello+]*/ +]]]*///", "hello +]]]*///");
        testMessage("hey /*[+hello+]*/", "hey hello");
        testMessage("hey /*[+hello+]*/ +]]]*///", "hey hello +]]]*///");
        testMessage("hey /*[+hello+]*/ +]]]*///", "hey hello +]]]*///");
        testMessage("hey /*[+hello+]*/ +]*/", "hey hello +]*/");
        testMessage("hey /*[+hello+]*/ +]*/", "hey hello +]*/");
        testMessage("/*[+ hello +]*/ +]]]*///", " hello  +]]]*///");
        testMessage("/*[+ hello +]*/ +]]]*/// */", " hello  +]]]*/// */");
        testMessage("/*[+ hello +]*/ +]]]*/// /*[[[", " hello  +]]]*/// /*[[[");
        testMessage("/*[+ hello +]*/ +]]]*/// /*[[[ */", " hello  +]]]*/// /*[[[ */");
        testMessage("/*[+ hello +]*/ +]]]*/// /*[[[ +]]]*///*/", " hello  +]]]*/// /*[[[ +]]]*///*/");
        testMessage("/*[+ hello +]*/ +]]]*/// /*[[[ +]]]*///", " hello  +]]]*/// /*[[[ +]]]*///");
        testMessage("/*[+hello +]*/ +]]]*///", "hello  +]]]*///");
        testMessage("/*[+hello +]*/ +]]]*/// */", "hello  +]]]*/// */");
        testMessage("/*[+hello +]*/ +]]]*/// /*[[[", "hello  +]]]*/// /*[[[");
        testMessage("/*[+hello +]*/ +]]]*/// /*[[[ */", "hello  +]]]*/// /*[[[ */");
        testMessage("/*[+hello +]*/ +]]]*/// /*[[[ +]]]*///*/", "hello  +]]]*/// /*[[[ +]]]*///*/");
        testMessage("/*[+hello +]*/ +]]]*/// /*[[[ +]]]*///", "hello  +]]]*/// /*[[[ +]]]*///");
        testMessage("/*[[[/*[+hello +]*/ +]]]*/// /*[[[ +]]]*///", "/*[[[hello  +]]]*/// /*[[[ +]]]*///");

    }







    private void testMessage(final String message, final String expected) throws IOException {

        for (int j = 1; j <= (message.length() + 10); j++) {

            for (int k = 1; k <= j; k++) {

                for (int l = 0; l < k; l++) {

                    final Reader stringReader =
                            new PrototypeOnlyCommentTextReader(new StringReader(message));

                    final char[] buffer = new char[j];

                    final StringBuilder strBuilder = new StringBuilder();
                    int read = 0;
                    while (read >= 0) {
                        read = stringReader.read(buffer, l, (k - l));
                        if (read >= 0) {
                            strBuilder.append(buffer, l, read);
                        }
                    }

                    final String result = strBuilder.toString();

                    Assertions.assertEquals(expected, result, "Checking: '" + message + "' (" + j + "," + k + "," + l + ")");

                }

            }

        }

    }



    private static String[] computeAllPrototypeOnlyMessages() {

        final List<String> allMessages = new ArrayList<String>();

        final String prefix = "/*[+";
        final String suffix = "+]*/";
        final String message = "0123456789";


        for (int i = 0; i <= message.length(); i++) {

            final StringBuilder msb1 = new StringBuilder(message);
            msb1.insert(i, suffix);

            for (int j = 0; j <= i; j++) {

                final StringBuilder msb2 = new StringBuilder(msb1);
                msb2.insert(j, prefix);

                for (int k = 0; k <= j; k++) {

                    final StringBuilder msb3 = new StringBuilder(msb2);
                    msb3.insert(k, suffix);

                    allMessages.add(msb3.toString());

                    for (int l = 0; l <= k; l++) {

                        final StringBuilder msb4 = new StringBuilder(msb3);
                        msb4.insert(l, prefix);

                        allMessages.add(msb4.toString());

                    }

                }

            }

        }

        return allMessages.toArray(new String[allMessages.size()]);

    }



    private static String computePrototypeOnlyEquivalent(final String message) {

        final StringBuilder stringBuilder = new StringBuilder();
        final int messageLen = message.length();
        boolean wasOpen = false;
        boolean inOpenStructure = false;
        boolean inCloseStructure = false;
        for (int i = 0; i < messageLen; i++) {
            if (!inOpenStructure && !inCloseStructure && message.charAt(i) == '/' && (i + 1) < messageLen && message.charAt(i + 1) == '*') {
                inOpenStructure = true;
                continue;
            } else if (!inOpenStructure && !inCloseStructure && wasOpen && message.charAt(i) == '+') {
                inCloseStructure = true;
                continue;
            } else if (inCloseStructure && message.charAt(i) == '/' && i > 0 && message.charAt(i - 1) == '*') {
                inCloseStructure = false;
                wasOpen = false;
                continue;
            } else if (inOpenStructure && message.charAt(i) == '+') {
                inOpenStructure = false;
                wasOpen = true;
                continue;
            }
            if (!inOpenStructure && !inCloseStructure) {
                stringBuilder.append(message.charAt(i));
            }
        }
        return stringBuilder.toString();
    }




}
