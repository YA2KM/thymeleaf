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
package org.thymeleaf.model;

/**
 * <p>
 *   Event interface defining a CDATA Section.
 * </p>
 * <p>
 *   Note that any implementations of this interface should be <strong>immutable</strong>.
 * </p>
 *
 * @author Daniel Fern&aacute;ndez
 * @since 3.0.0
 * 
 */
public interface ICDATASection extends ITemplateEvent, CharSequence {

    /**
     * <p>
     *   Returns the whole CDATA Section, including the {@code <![CDATA[...]]>} prefix and suffix.
     * </p>
     *
     * @return the CDATA Section.
     */
    public String getCDATASection();

    /**
     * <p>
     *   Returns the content of the CDATA Section, without the prefix or suffix.
     * </p>
     *
     * @return the content of the CDATA Section.
     */
    public String getContent();

}
