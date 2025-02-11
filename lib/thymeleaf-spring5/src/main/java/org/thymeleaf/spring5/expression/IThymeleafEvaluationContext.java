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
package org.thymeleaf.spring5.expression;

import org.springframework.expression.EvaluationContext;
import org.thymeleaf.expression.IExpressionObjects;

/**
 * <p>
 *   Interface for Thymeleaf-specific implementations of {@link EvaluationContext}.
 * </p>
 *
 * @see ThymeleafEvaluationContext
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.3
 *
 */
public interface IThymeleafEvaluationContext extends EvaluationContext {

    public boolean isVariableAccessRestricted();
    public void setVariableAccessRestricted(final boolean restricted);

    public IExpressionObjects getExpressionObjects();
    public void setExpressionObjects(final IExpressionObjects expressionObjects);

}
