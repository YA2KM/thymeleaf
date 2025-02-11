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
package org.thymeleaf.engine;

import org.thymeleaf.context.IEngineContext;

/*
 *
 * @author Daniel Fernandez
 * @since 3.0.0
 *
 */
final class DecreaseContextLevelProcessable implements IEngineProcessable {

    private final IEngineContext context;
    private final TemplateFlowController flowController;


    DecreaseContextLevelProcessable(final IEngineContext context, final TemplateFlowController flowController) {
        super();
        this.context = context;
        this.flowController = flowController;
    }


    public boolean process() {

        /*
         * First, check the stopProcess flag
         */
        if (this.flowController.stopProcessing) {
            return false;
        }

        /*
         * Process the queue
         */
        if (this.context != null) {
            this.context.decreaseLevel();
        }

        return true;

    }


}