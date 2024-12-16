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
package org.thymeleaf.templateengine.context;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.thymeleaf.testing.templateengine.engine.TestExecutorFactory;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.util.ThrottleArgumentsProvider;
import org.thymeleaf.templateengine.context.dialect.ContextDialect;
import org.thymeleaf.templateengine.context.dialect.ContextVarTestDialect;
import org.thymeleaf.testing.templateengine.engine.TestExecutor;
import org.thymeleaf.util.ThrottledWebTestExecutorArgumentsProvider;


public class ContextTest {





    @ParameterizedTest
    @ArgumentsSource(ThrottledWebTestExecutorArgumentsProvider.class)
    public void testContextBase(final TestExecutor executor) throws Exception {

        executor.setDialects(
                Arrays.asList(new IDialect[] { new StandardDialect(), new ContextDialect()}));
        executor.execute("classpath:templateengine/context/base");

        Assertions.assertTrue(executor.isAllOK());

    }

    @ParameterizedTest
    @ArgumentsSource(ThrottledWebTestExecutorArgumentsProvider.class)
    public void testContextVarTest(final TestExecutor executor) throws Exception {

        executor.setDialects(
                Arrays.asList(new IDialect[] { new StandardDialect(), new ContextVarTestDialect()}));
        executor.execute("classpath:templateengine/context/vartest");

        Assertions.assertTrue(executor.isAllOK());

    }

    
}
