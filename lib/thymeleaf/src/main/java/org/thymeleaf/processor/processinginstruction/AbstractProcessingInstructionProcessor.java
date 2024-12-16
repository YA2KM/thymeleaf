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
package org.thymeleaf.processor.processinginstruction;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.IProcessingInstruction;
import org.thymeleaf.processor.AbstractProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * <p>
 *   Basic abstract implementation of {@link IProcessingInstructionProcessor}.
 * </p>
 * <p>
 *   This abstract implementation takes care of correct exception handling so that subclasses don't have to.
 * </p>
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.0
 *
 */
public abstract class AbstractProcessingInstructionProcessor
        extends AbstractProcessor implements IProcessingInstructionProcessor {



    public AbstractProcessingInstructionProcessor(final TemplateMode templateMode, final int precedence) {
        super(templateMode, precedence);
    }


    public final void process(
            final ITemplateContext context,
            final IProcessingInstruction processingInstruction, final IProcessingInstructionStructureHandler structureHandler) {

        try {

            doProcess(context, processingInstruction, structureHandler);

        } catch (final TemplateProcessingException e) {
            if (processingInstruction.hasLocation()) {
                if (!e.hasTemplateName()) {
                    e.setTemplateName(processingInstruction.getTemplateName());
                }
                if (!e.hasLineAndCol()) {
                    e.setLineAndCol(processingInstruction.getLine(), processingInstruction.getCol());
                }
            }
            throw e;
        } catch (final Exception e) {
            throw new TemplateProcessingException(
                    "Error during execution of processor '" + this.getClass().getName() + "'",
                    processingInstruction.getTemplateName(), processingInstruction.getLine(), processingInstruction.getCol(), e);
        }

    }


    protected abstract void doProcess(
            final ITemplateContext context,
            final IProcessingInstruction processingInstruction, final IProcessingInstructionStructureHandler structureHandler);

}
