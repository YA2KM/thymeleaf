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
package org.thymeleaf.templateengine.conversion.conversion7;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.DataBinder;
import org.thymeleaf.testing.templateengine.spring5.context.web.SpringMVCWebProcessingContextBuilder;
import org.thymeleaf.testing.templateengine.testable.ITest;


public class Conversion7WebProcessingContextBuilder extends SpringMVCWebProcessingContextBuilder {



    public Conversion7WebProcessingContextBuilder() {
        super();
        setApplicationContextConfigLocation("classpath:templateengine/conversion/conversion7/applicationContext.xml");
    }

    
    @Override
    protected void initBinder(
            final String bindingVariableName, final Object bindingObject,
            final ITest test, final DataBinder dataBinder, final Locale locale, 
            final Map<String,Object> variables) {

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy//MM//dd");
        dataBinder.registerCustomEditor(Date.class, "inner.date", new CustomDateEditor(dateFormat, true));

    }

    
}
