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
package org.thymeleaf.dialect.dialectwrapping;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class Dialect01 extends AbstractProcessorDialect {


    public Dialect01() {
        super("Dialect01", null, 100);
    }


    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new ElementTagProcessor(dialectPrefix));
        processors.add(new ElementModelProcessor(dialectPrefix));
        processors.add(new CDATASectionProcessor());
        processors.add(new CommentProcessor());
        processors.add(new DocTypeProcessor());
        processors.add(new ProcessingInstructionProcessor());
        processors.add(new TemplateBoundariesProcessor());
        processors.add(new TextProcessor());
        processors.add(new XMLDeclarationProcessor());
        return processors;
    }

}
