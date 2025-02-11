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
package org.thymeleaf.templateengine.conversion.conversion6;

import org.springframework.format.annotation.NumberFormat;

public class Conversion6Bean01 {

    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private Integer num = null;


    public Conversion6Bean01() {
        super();
    }


    public Integer getNum() {
        return this.num;
    }

    public void setNum(final Integer num) {
        this.num = num;
    }

    public Integer obtainNum() {
        return this.num;
    }

    @Override
    public String toString() {
        return "FormBean04{" +
                "num=" + num +
                '}';
    }
}
