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
package org.thymeleaf.context;

import org.thymeleaf.web.IWebExchange;

/**
 * <p>
 *   Specialization of the {@link IContext} interface to be implemented by contexts used for template
 *   processing in web environments.
 * </p>
 * <p>
 *   Objects implementing this interface add to the usual {@link IContext} data the web related
 *   artifacts needed to perform web-oriented functions such as URL rewriting or request/session access.
 * </p>
 * <p>
 *   Note this interface was modified in a backwards-incompatible way in Thymeleaf 3.1.0.
 * </p>
 *
 * @author Daniel Fern&aacute;ndez
 *
 * @since 3.1.0
 *
 */
public interface IWebContext extends IContext {

	/**
	 * <p>
	 *   Returns the {@link IWebExchange} object associated with the template execution.
	 * </p>
	 *
	 * @return the web exchange object.
	 */
	public IWebExchange getExchange();

}
