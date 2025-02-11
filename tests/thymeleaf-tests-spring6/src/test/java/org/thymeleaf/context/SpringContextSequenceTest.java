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
package org.thymeleaf.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.testing.templateengine.util.ResourceUtils;
import org.thymeleaf.util.ClassLoaderUtils;

public class SpringContextSequenceTest {



    public SpringContextSequenceTest() {
        super();
    }





    @Test
    public void testContextSequenceSpring() throws Exception {

        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(new ClassLoaderTemplateResolver());

        final Context ctx1 = new Context();
        ctx1.setVariable("myObject", new MyObjectClass("one"));

        final Context ctx2 = new Context();
        ctx2.setVariable("myObject", new MyObjectClass("two"));

        final String result1 =
                ResourceUtils.normalize(templateEngine.process("context/contextSequence.html", ctx1));
        final String result2 =
                ResourceUtils.normalize(templateEngine.process("context/contextSequence.html", ctx2));

        final String expected1 =
                ResourceUtils.read(
                        ClassLoaderUtils.getClassLoader(SpringContextSequenceTest.class).getResourceAsStream("context/contextSequence-result1.html"),
                        "UTF-8", true);
        final String expected2 =
                ResourceUtils.read(
                        ClassLoaderUtils.getClassLoader(SpringContextSequenceTest.class).getResourceAsStream("context/contextSequence-result2.html"),
                        "UTF-8", true);

        Assertions.assertEquals(expected1, result1);
        Assertions.assertEquals(expected2, result2);

    }




    public static class MyObjectClass {

        private final String foo;

        public MyObjectClass(final String foo) {
            super();
            this.foo = foo;
        }

        public String getFoo() {
            return this.foo;
        }

    }

}
