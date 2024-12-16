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
package org.thymeleaf.standard.expression;

import java.util.Map;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Assertions;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.ExpressionContext;
import org.thymeleaf.context.IExpressionContext;

/**
 * 
 * @author Daniel Fern&aacute;ndez
 * 
 * @since 1.1
 *
 */
public class ExpressionBenchmark {

    
    
    public ExpressionBenchmark() {
        super();
    }
    
    
    public static void main(String[] args) throws Exception {

        final Map<String,String> expressionsMap = ExpressionBenchmarkDefinitions.createExpressionsMap();

        final TemplateEngine templateEngine = new TemplateEngine();
        final IEngineConfiguration configuration = templateEngine.getConfiguration();

        final IExpressionContext processingContext = new ExpressionContext(configuration);

        final IStandardExpressionParser parser = new StandardExpressionParser();

        for (final Map.Entry<String,String> expressionEntry : expressionsMap.entrySet()) {
            final String expression = expressionEntry.getKey();
            final String expectedParsingResult = expressionEntry.getValue();
            final IStandardExpression parsedExpression = parser.parseExpression(processingContext, expression);
            Assertions.assertNotNull(parsedExpression);
            final String exp = parsedExpression.getStringRepresentation();
            Assertions.assertEquals(expectedParsingResult, exp);
        }
        
        
        
        final StopWatch sw = new StopWatch();
        
        sw.start();
        
        
        for (int x = 0; x < 1000; x++)
            for (final String expression : expressionsMap.keySet())
                parser.parseExpression(processingContext, expression);

        sw.stop();
        
        System.out.println("First pass: " + sw.toString());
        
        sw.reset();
        sw.start();
        
        for (int x = 0; x < 1000; x++)
            for (final String expression : expressionsMap.keySet())
                parser.parseExpression(processingContext, expression);


        sw.stop();
        
        System.out.println("Second pass: " + sw.toString());
        
    }

    
    
    
    
}
