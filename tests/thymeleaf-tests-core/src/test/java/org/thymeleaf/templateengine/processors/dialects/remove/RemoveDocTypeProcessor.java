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
package org.thymeleaf.templateengine.processors.dialects.remove;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IDocType;
import org.thymeleaf.processor.doctype.AbstractDocTypeProcessor;
import org.thymeleaf.processor.doctype.IDocTypeStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class RemoveDocTypeProcessor extends AbstractDocTypeProcessor {

    private static final int PRECEDENCE = 1000;


    public RemoveDocTypeProcessor() {
        super(TemplateMode.HTML, PRECEDENCE);
    }


    @Override
    protected void doProcess(
            final ITemplateContext context, final IDocType docType, final IDocTypeStructureHandler structureHandler) {

        structureHandler.removeDocType();

    }

}
