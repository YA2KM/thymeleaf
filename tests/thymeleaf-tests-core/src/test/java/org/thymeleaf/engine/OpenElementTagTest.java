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

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.TestTemplateEngineConfigurationBuilder;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IOpenElementTag;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateparser.markup.HTMLTemplateParser;
import org.thymeleaf.templateparser.markup.XMLTemplateParser;
import org.thymeleaf.templateresource.StringTemplateResource;


public final class OpenElementTagTest {

    private static final HTMLTemplateParser HTML_PARSER = new HTMLTemplateParser(2, 4096);
    private static final XMLTemplateParser XML_PARSER = new XMLTemplateParser(2, 4096);
    private static final IEngineConfiguration TEMPLATE_ENGINE_CONFIGURATION = TestTemplateEngineConfigurationBuilder.build();




    @Test
    public void testHtmlOpenElementAttrManagement() {

        final AttributeDefinitions attributeDefinitions = new AttributeDefinitions(Collections.EMPTY_MAP);

        OpenElementTag tag;

        tag = computeHtmlTag("<div>");
        Assertions.assertEquals("<div>", tag.toString());

        tag = computeHtmlTag("<div type=\"text\">");
        Assertions.assertEquals("<div type=\"text\">", tag.toString());

        tag = computeHtmlTag("<div type=\"text\"   value='hello!!!'>");
        Assertions.assertEquals("<div type=\"text\"   value='hello!!!'>", tag.toString());
        tag = tag.removeAttribute("type");
        Assertions.assertEquals("<div value='hello!!!'>", tag.toString());
        tag = tag.removeAttribute("value");
        Assertions.assertEquals("<div>", tag.toString());

        tag = computeHtmlTag("<div type=\"text\"   value='hello!!!'    >");
        Assertions.assertEquals("<div type=\"text\"   value='hello!!!'    >", tag.toString());
        tag = tag.removeAttribute(null, "type");
        Assertions.assertEquals("<div value='hello!!!'    >", tag.toString());
        tag = tag.removeAttribute(null, "value");
        Assertions.assertEquals("<div    >", tag.toString());

        tag = computeHtmlTag("<div type=\"text\"   value='hello!!!'    ba >");
        Assertions.assertEquals("<div type=\"text\"   value='hello!!!'    ba >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "value", "bye! :(", null);
        Assertions.assertEquals("<div type=\"text\"   value='bye! :('    ba >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "type", "one", null);
        Assertions.assertEquals("<div type=\"one\"   value='bye! :('    ba >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "two", null);
        Assertions.assertEquals("<div type=\"one\"   value='bye! :('    ba=\"two\" >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "three", AttributeValueQuotes.SINGLE);
        Assertions.assertEquals("<div type=\"one\"   value='bye! :('    ba='three' >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "four", AttributeValueQuotes.NONE);
        Assertions.assertEquals("<div type=\"one\"   value='bye! :('    ba=four >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "five", null);
        Assertions.assertEquals("<div type=\"one\"   value='bye! :('    ba=five >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", null, null);
        Assertions.assertEquals("<div type=\"one\"   value='bye! :('    ba >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "six", null);
        Assertions.assertEquals("<div type=\"one\"   value='bye! :('    ba=\"six\" >", tag.toString());

        tag = computeHtmlTag("<div type=\"text\"   value='hello!!!'    ba=twenty >");
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "thirty", null);
        Assertions.assertEquals("<div type=\"text\"   value='hello!!!'    ba=thirty >", tag.toString());

        tag = computeHtmlTag("<div type=\"text\"   value='hello!!!'    ba=twenty ><p id='one'>");
        Assertions.assertEquals("<p id='one'>", tag.toString());

    }




    @Test
    public void testXmlOpenElementAttrManagement() {

        final AttributeDefinitions attributeDefinitions = new AttributeDefinitions(Collections.EMPTY_MAP);

        OpenElementTag tag;

        tag = computeXmlTag("<input></input>");
        Assertions.assertEquals("<input>", tag.toString());

        tag = computeXmlTag("<input type=\"text\"></input>");
        Assertions.assertEquals("<input type=\"text\">", tag.toString());

        tag = computeXmlTag("<input type=\"text\"   value='hello!!!'></input>");
        Assertions.assertEquals("<input type=\"text\"   value='hello!!!'>", tag.toString());
        tag = tag.removeAttribute("type");
        Assertions.assertEquals("<input value='hello!!!'>", tag.toString());
        tag = tag.removeAttribute("value");
        Assertions.assertEquals("<input>", tag.toString());

        tag = computeXmlTag("<input type=\"text\"   value='hello!!!'    ></input>");
        Assertions.assertEquals("<input type=\"text\"   value='hello!!!'    >", tag.toString());
        tag = tag.removeAttribute(null, "type");
        Assertions.assertEquals("<input value='hello!!!'    >", tag.toString());
        tag = tag.removeAttribute(null, "value");
        Assertions.assertEquals("<input    >", tag.toString());

        tag = computeXmlTag("<input th:type=\"text\"   th:value='hello!!!'    ></input>");
        Assertions.assertEquals("<input th:type=\"text\"   th:value='hello!!!'    >", tag.toString());
        tag = tag.removeAttribute("th", "type");
        Assertions.assertEquals("<input th:value='hello!!!'    >", tag.toString());
        tag = tag.removeAttribute("th", "value");
        Assertions.assertEquals("<input    >", tag.toString());

        tag = computeXmlTag("<input type=\"text\"   value='hello!!!'    ba='' ></input>");
        Assertions.assertEquals("<input type=\"text\"   value='hello!!!'    ba='' >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "value", "bye! :(", null);
        Assertions.assertEquals("<input type=\"text\"   value='bye! :('    ba='' >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "type", "one", null);
        Assertions.assertEquals("<input type=\"one\"   value='bye! :('    ba='' >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "two", null);
        Assertions.assertEquals("<input type=\"one\"   value='bye! :('    ba='two' >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "three", AttributeValueQuotes.SINGLE);
        Assertions.assertEquals("<input type=\"one\"   value='bye! :('    ba='three' >", tag.toString());

        try {
            tag = tag.setAttribute(attributeDefinitions, null, "ba", "four", AttributeValueQuotes.NONE);
            Assertions.assertTrue(false);
        } catch (final IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }

        try {
            tag = tag.setAttribute(attributeDefinitions, null, "ba", null, AttributeValueQuotes.NONE);
            Assertions.assertTrue(false);
        } catch (final IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }

        try {
            tag = tag.setAttribute(attributeDefinitions, null, "ba", null, null);
            Assertions.assertTrue(false);
        } catch (final IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }

        tag = tag.setAttribute(attributeDefinitions, null, "ba", "five", null);
        Assertions.assertEquals("<input type=\"one\"   value='bye! :('    ba='five' >", tag.toString());
        tag = tag.setAttribute(attributeDefinitions, null, "ba", "six", null);
        Assertions.assertEquals("<input type=\"one\"   value='bye! :('    ba='six' >", tag.toString());

        tag = computeXmlTag("<div type=\"text\"   value='hello!!!'    ba='twenty' ><p id='one'></p></div>");
        Assertions.assertEquals("<p id='one'>", tag.toString());

    }







    private static OpenElementTag computeHtmlTag(final String input) {

        final String templateName = "test";
        final TagObtentionTemplateHandler handler = new TagObtentionTemplateHandler();

        HTML_PARSER.parseStandalone(TEMPLATE_ENGINE_CONFIGURATION, templateName, templateName, null, new StringTemplateResource(input), TemplateMode.HTML, false, handler);

        return handler.tag;

    }




    private static OpenElementTag computeXmlTag(final String input) {

        final String templateName = "test";
        final TagObtentionTemplateHandler handler = new TagObtentionTemplateHandler();

        XML_PARSER.parseStandalone(TEMPLATE_ENGINE_CONFIGURATION, templateName, templateName, null, new StringTemplateResource(input), TemplateMode.XML, false, handler);

        return handler.tag;

    }




    private static class TagObtentionTemplateHandler extends AbstractTemplateHandler {


        OpenElementTag tag;


        @Override
        public void handleOpenElement(final IOpenElementTag openElementTag) {
            this.tag = OpenElementTag.asEngineOpenElementTag(openElementTag);
        }

    }


}
