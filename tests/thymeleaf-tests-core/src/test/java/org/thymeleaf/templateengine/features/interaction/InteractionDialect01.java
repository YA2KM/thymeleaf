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
package org.thymeleaf.templateengine.features.interaction;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.templatemode.TemplateMode;

public class InteractionDialect01 extends AbstractProcessorDialect {


    public InteractionDialect01() {
        super("InteractionDialect01", null, StandardDialect.PROCESSOR_PRECEDENCE);
    }


    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new InteractionDialect01TextProcessor(TemplateMode.HTML));
        processors.add(new InteractionDialect01CDATASectionProcessor(TemplateMode.HTML));
        processors.add(new InteractionDialect01CommentProcessor(TemplateMode.HTML));
        processors.add(new InteractionDialect01TextProcessor(TemplateMode.JAVASCRIPT));
        processors.add(new InteractionDialect01CDATASectionProcessor(TemplateMode.JAVASCRIPT));
        processors.add(new InteractionDialect01CommentProcessor(TemplateMode.JAVASCRIPT));
        processors.add(new InteractionDialect01TextProcessor(TemplateMode.CSS));
        processors.add(new InteractionDialect01CDATASectionProcessor(TemplateMode.CSS));
        processors.add(new InteractionDialect01CommentProcessor(TemplateMode.CSS));
        return processors;
    }

}
