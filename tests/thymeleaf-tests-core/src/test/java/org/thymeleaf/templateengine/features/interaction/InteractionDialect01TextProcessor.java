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

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IText;
import org.thymeleaf.processor.text.AbstractTextProcessor;
import org.thymeleaf.processor.text.ITextStructureHandler;
import org.thymeleaf.standard.processor.StandardInliningTextProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public class InteractionDialect01TextProcessor extends AbstractTextProcessor {


    public InteractionDialect01TextProcessor(final TemplateMode templateMode) {
        super(templateMode, StandardInliningTextProcessor.PRECEDENCE + 10); // We want this to happen AFTER inlining
    }




    @Override
    protected void doProcess(
            final ITemplateContext processingContext, final IText text, final ITextStructureHandler structureHandler) {

        structureHandler.setText("||" + text.getText() + "||");

    }

}
