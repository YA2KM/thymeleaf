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

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.inline.IInliner;
import org.thymeleaf.inline.NoOpInliner;
import org.thymeleaf.model.IComment;
import org.thymeleaf.processor.comment.AbstractCommentProcessor;
import org.thymeleaf.processor.comment.ICommentStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author Daniel Fern&aacute;ndez
 * @since 3.0.0
 */
public final class StandardInliningCommentProcessor extends AbstractCommentProcessor {

    public static final int PRECEDENCE = 1000;

    public StandardInliningCommentProcessor(final TemplateMode templateMode) {
        super(templateMode, PRECEDENCE);
    }


    @Override
    protected void doProcess(
            final ITemplateContext context,
            final IComment comment, final ICommentStructureHandler structureHandler) {


        final IInliner inliner = context.getInliner();

        if (inliner == null || inliner == NoOpInliner.INSTANCE) {
            return;
        }

        final CharSequence inlined = inliner.inline(context, comment);
        if (inlined != null && inlined != comment) {
            structureHandler.setContent(inlined);
        }

    }

}
