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
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeModelProcessor;
import org.thymeleaf.processor.element.IElementModelStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

public class MarkupPrintAfterElementModelProcessor extends AbstractAttributeModelProcessor {

    public static final String ATTR_NAME = "printafter";


    public MarkupPrintAfterElementModelProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, 1500, true);
    }



    @Override
    protected void doProcess(final ITemplateContext context,
                             final IModel model,
                             final AttributeName attributeName, final String attributeValue,
                             final IElementModelStructureHandler structureHandler) {

        final String markupStr = HtmlEscape.escapeHtml4Xml(model.toString().replaceAll("\\r\\n|\\r|\\n", "\\\\n"));

        final IModelFactory modelFactory = context.getModelFactory();

        final IProcessableElementTag newTag = modelFactory.setAttribute((IProcessableElementTag) model.get(0), "aggafter", markupStr);

        model.replace(0, newTag);

    }

}
