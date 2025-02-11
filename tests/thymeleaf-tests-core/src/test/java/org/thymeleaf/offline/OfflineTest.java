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
package org.thymeleaf.offline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.testing.templateengine.util.ResourceUtils;
import org.thymeleaf.util.ClassLoaderUtils;





public class OfflineTest {
    
    
    public OfflineTest() {
        super();
    }
    
    
    
    @Test
    public void testOffline01() throws Exception {

        final Context ctx = new Context();
        ctx.setVariable("one", "This is one");
        
        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(new ClassLoaderTemplateResolver());
        final String result = templateEngine.process("offline/offline01.html", ctx);

        final String expected = 
                ResourceUtils.read(
                        ClassLoaderUtils.getClassLoader(OfflineTest.class).getResourceAsStream("offline/offline01-result.html"),
                        "UTF-8",
                        true);
        
        Assertions.assertEquals(expected, ResourceUtils.normalize(result));
        
    }
    
    

    
    
}
