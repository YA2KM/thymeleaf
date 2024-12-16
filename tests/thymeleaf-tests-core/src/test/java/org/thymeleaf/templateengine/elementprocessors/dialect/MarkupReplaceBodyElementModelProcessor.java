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
package org.thymeleaf.templateengine.elementprocessors.dialect;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.processor.element.AbstractAttributeModelProcessor;
import org.thymeleaf.processor.element.IElementModelStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class MarkupReplaceBodyElementModelProcessor extends AbstractAttributeModelProcessor {

    public static final String ATTR_NAME = "replacebody";

    public static final String REPLACEMENT = "<p>This is a <span th:text=\"replacement\">prototype</span></p>";


    public MarkupReplaceBodyElementModelProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, 1000, true);
    }



    @Override
    protected void doProcess(final ITemplateContext context,
                             final IModel model,
                             final AttributeName attributeName, final String attributeValue,
                             final IElementModelStructureHandler structureHandler) {

        final IModel replacementMarkup = context.getModelFactory().parse(context.getTemplateData(), REPLACEMENT);


        for (int i = model.size() - 2; i > 0; i--) {
            model.remove(i);
        }

        model.insertModel(1, replacementMarkup);

    }

}
