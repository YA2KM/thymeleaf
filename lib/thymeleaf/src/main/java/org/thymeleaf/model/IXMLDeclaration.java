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
 *   Event interface defining an XML declaration.
 * </p>
 * <p>
 *   Note that any implementations of this interface should be <strong>immutable</strong>.
 * </p>
 *
 * @author Daniel Fern&aacute;ndez
 * @since 3.0.0
 * 
 */
public interface IXMLDeclaration extends ITemplateEvent {

    /**
     * <p>
     *   Returns the keyword of the XML Declaration in its original
     *   case (usually {@code xml}).
     * </p>
     *
     * @return the XML Declaration keyword.
     */
    public String getKeyword();

    /**
     * <p>
     *   Returns the XML version specified at the XML Declaration (if specified).
     * </p>
     *
     * @return the XML version (might be null).
     */
    public String getVersion();

    /**
     * <p>
     *   Returns the value of the {@code encoding} attribute specified at the
     *   XML Declaration (if specified).
     * </p>
     *
     * @return the encoding value (might be null).
     */
    public String getEncoding();

    /**
     * <p>
     *   Returns the value of the {@code standalone} attribute specified at the
     *   XML Declaration (if specified).
     * </p>
     *
     * @return the standalone value (might be null).
     */
    public String getStandalone();

    /**
     * <p>
     *   Returns the complete XML Declaration as a String.
     * </p>
     *
     * @return the complete XML Declaration.
     */
    public String getXmlDeclaration();

}
