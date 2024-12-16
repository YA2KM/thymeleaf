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
package org.thymeleaf.templateengine.context.dialect;

import java.util.Map;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.ICloseElementTag;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IOpenElementTag;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.model.IStandaloneElementTag;
import org.thymeleaf.model.ITemplateEvent;
import org.thymeleaf.processor.element.AbstractAttributeModelProcessor;
import org.thymeleaf.processor.element.IElementModelStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class ModelAttributeTagProcessor extends AbstractAttributeModelProcessor {

    /*
     * This processor will modify the name of the tag it is executed for.
     * The idea is to test that a local variable set at the very beginning lives through until the end of the tag processing.
     */

    public ModelAttributeTagProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, "model", true, 250, true);
    }


    @Override
    protected void doProcess(final ITemplateContext context, final IModel model, final AttributeName attributeName, final String attributeValue, final IElementModelStructureHandler structureHandler) {

        final IModelFactory modelFactory = context.getModelFactory();

        final IProcessableElementTag firstEvent = (IProcessableElementTag) model.get(0);
        final Map<String,String> attributes = firstEvent.getAttributeMap();

        final ITemplateEvent lastEvent = model.get(model.size() - 1);

        if (firstEvent == lastEvent) {

            final IStandaloneElementTag newFirstEvent =
                    modelFactory.createStandaloneElementTag("ctx", attributes, AttributeValueQuotes.DOUBLE, false, false);
            model.replace(0, newFirstEvent);

        } else {

            final IOpenElementTag newFirstEvent =
                    modelFactory.createOpenElementTag("ctx", attributes, AttributeValueQuotes.DOUBLE, false);
            final ICloseElementTag newLastEvent =
                    modelFactory.createCloseElementTag("ctx", false, false);

            model.replace(0, newFirstEvent);
            model.replace(model.size() - 1, newLastEvent);

        }

    }


}
