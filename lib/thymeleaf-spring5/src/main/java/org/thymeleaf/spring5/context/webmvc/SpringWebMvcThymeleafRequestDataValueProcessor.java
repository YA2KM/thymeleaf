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
package org.thymeleaf.spring5.context.webmvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestDataValueProcessor;
import org.thymeleaf.spring5.context.IThymeleafRequestDataValueProcessor;

/**
 * <p>
 *   Implementation of the {@link IThymeleafRequestDataValueProcessor} interface, meant to wrap a Spring
 *   {@link RequestDataValueProcessor} object.
 * </p>
 *
 * @see RequestDataValueProcessor
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.0.3
 *
 */
class SpringWebMvcThymeleafRequestDataValueProcessor implements IThymeleafRequestDataValueProcessor {


    private final RequestDataValueProcessor requestDataValueProcessor;
    private final HttpServletRequest httpServletRequest;


    SpringWebMvcThymeleafRequestDataValueProcessor(
            final RequestDataValueProcessor requestDataValueProcessor, final HttpServletRequest httpServletRequest) {
        super();
        this.requestDataValueProcessor = requestDataValueProcessor;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String processAction(final String action, final String httpMethod) {
        if (this.requestDataValueProcessor == null) {
            // The presence of a Request Data Value Processor is optional
            return action;
        }
        return this.requestDataValueProcessor.processAction(this.httpServletRequest, action, httpMethod);
    }

    @Override
    public String processFormFieldValue(final String name, final String value, final String type) {
        if (this.requestDataValueProcessor == null) {
            // The presence of a Request Data Value Processor is optional
            return value;
        }
        return this.requestDataValueProcessor.processFormFieldValue(this.httpServletRequest, name, value, type);
    }

    @Override
    public Map<String, String> getExtraHiddenFields() {
        if (this.requestDataValueProcessor == null) {
            // The presence of a Request Data Value Processor is optional
            return null;
        }
        return this.requestDataValueProcessor.getExtraHiddenFields(this.httpServletRequest);
    }

    @Override
    public String processUrl(final String url) {
        if (this.requestDataValueProcessor == null) {
            // The presence of a Request Data Value Processor is optional
            return url;
        }
        return this.requestDataValueProcessor.processUrl(this.httpServletRequest, url);
    }

}
