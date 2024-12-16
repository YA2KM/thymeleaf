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
package org.thymeleaf.standard.expression;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FragmentSignatureTest {


    public FragmentSignatureTest() {
        super();
    }



    @Test
    public void testFragmentSignature() throws Exception {
        check("frag", "frag", null);
        check("     frag ", "frag", null);
        check("     frag ()", "frag", null);
        check("     frag (as)", "frag", new String[] {"as"});
        check("     frag ( asd , 231fa., asaad    )", "frag", new String[] {"asd", "231fa.", "asaad"});
    }


    private static void check(final String fragmentSpec, final String fragmentName, final String[] parameterNames) {
        final FragmentSignature signature = FragmentSignatureUtils.internalParseFragmentSignature(fragmentSpec);
        Assertions.assertEquals(fragmentName, signature.getFragmentName());
        if (parameterNames == null) {
            Assertions.assertNull(signature.getParameterNames());
        } else {
            Assertions.assertEquals(Arrays.asList(parameterNames),signature.getParameterNames());
        }
    }


}
