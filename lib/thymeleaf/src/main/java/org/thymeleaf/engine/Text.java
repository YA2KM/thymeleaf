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

import java.io.IOException;
import java.io.Writer;

import org.thymeleaf.model.IModelVisitor;
import org.thymeleaf.model.IText;

/*
 * Engine implementation of IText.
 *
 * @author Daniel Fernandez
 * @since 3.0.0
 */
final class Text extends AbstractTextualTemplateEvent implements IText {



    Text(final CharSequence text) {
        super(text);
    }


    Text(final CharSequence text, final String templateName, final int line, final int col) {
        super(text, templateName, line, col);
    }




    public String getText() {
        return getContentText();
    }


    public int length() {
        return getContentLength();
    }


    public char charAt(final int index) {
        return charAtContent(index);
    }


    public CharSequence subSequence(final int start, final int end) {
        return contentSubSequence(start, end);
    }




    public void accept(final IModelVisitor visitor) {
        visitor.visit(this);
    }


    public void write(final Writer writer) throws IOException {
        writeContent(writer);
    }




    // Meant to be called only from within the engine
    static Text asEngineText(final IText text) {
        if (text instanceof Text) {
            return (Text) text;
        }
        return new Text(text.getText(), text.getTemplateName(), text.getLine(), text.getCol());
    }




    @Override
    public void beHandled(final ITemplateHandler handler) {
        handler.handleText(this);
    }




    @Override
    public String toString() {
        return getText();
    }


}
