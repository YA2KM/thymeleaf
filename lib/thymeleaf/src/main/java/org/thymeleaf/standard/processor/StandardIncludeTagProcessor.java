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
package org.thymeleaf.standard.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.engine.AttributeNames;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.util.LoggingUtils;

/**
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.0
 * @deprecated Deprecated in Thymeleaf 3.0. This attribute will raise a warning since 3.1 and will
 *             be removed in version 3.2/4.0. Please use th:insert instead.
 */
@Deprecated
public final class StandardIncludeTagProcessor extends AbstractStandardFragmentInsertionTagProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardIncludeTagProcessor.class);

    public static final int PRECEDENCE = 100;
    public static final String ATTR_NAME = "include";





    public StandardIncludeTagProcessor(final TemplateMode templateMode, final String dialectPrefix) {
        super(templateMode, dialectPrefix, ATTR_NAME, PRECEDENCE, false, true);
    }



    @Override
    protected void doProcess(
            final ITemplateContext context,
            final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {

        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(
                    "[THYMELEAF][{}][{}] Deprecated attribute {} found in template {}, line {}, col {}. " +
                    "Please use {} instead, this deprecated attribute will be removed in future versions of Thymeleaf.",
                    new Object[]{
                            TemplateEngine.threadIndex(), LoggingUtils.loggifyTemplateName(context.getTemplateData().getTemplate()),
                            attributeName, tag.getTemplateName(),
                            Integer.valueOf(tag.getAttribute(attributeName).getLine()), Integer.valueOf(tag.getAttribute(attributeName).getCol()),
                            AttributeNames.forHTMLName(attributeName.getPrefix(), StandardInsertTagProcessor.ATTR_NAME)});
        }

        super.doProcess(context, tag, attributeName, attributeValue, structureHandler);

    }
}
