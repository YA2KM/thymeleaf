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
package org.thymeleaf.spring.view;

import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolverWrapper;

public class ThymeleafViewResolverTest {



    @Test
    public void testConfigureViewBean() throws Exception {

        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/view/applicationContext.xml");

        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setApplicationContext(context);
        resolver.setViewClass(TestThymeleafView.class);

        final View view = ThymeleafViewResolverWrapper.loadView(resolver, "testview", Locale.US);

        final String viewValue = ((TestThymeleafView)view).getSomething();
        Assertions.assertEquals("view_value", viewValue);

        final TestThymeleafView.ViewBean viewBean = ((TestThymeleafView)view).getViewBean();
        Assertions.assertNotNull(viewBean);

        final String beanValue = viewBean.getValue();
        Assertions.assertEquals("bean_value", beanValue);

    }


    @Test
    public void testConfigureViewNoBean() throws Exception {

        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/view/applicationContext.xml");

        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setApplicationContext(context);
        resolver.setViewClass(TestThymeleafView.class);

        // testview2 does not exist as a declared bean at the application context
        final View view = ThymeleafViewResolverWrapper.loadView(resolver,"testview2", Locale.US);

        // There's no matching view definition, so "something" should not be populated
        final String viewValue = ((TestThymeleafView)view).getSomething();
        Assertions.assertNull(viewValue);

        final TestThymeleafView.ViewBean viewBean = ((TestThymeleafView)view).getViewBean();
        Assertions.assertNotNull(viewBean);

        // This should be there, because it is applied through an @Autowired annotation
        final String beanValue = viewBean.getValue();
        Assertions.assertEquals("bean_value", beanValue);

    }


    @Test
    public void testConfigureViewConfiguringBean() throws Exception {

        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/view/applicationContext.xml");

        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setApplicationContext(context);
        resolver.setViewClass(TestThymeleafView.class);

        // testview2 does not exist as a declared bean at the application context
        final View view = ThymeleafViewResolverWrapper.loadView(resolver,"view_prototype", Locale.US);

        // There's no matching view definition, so "something" should not be populated
        final String viewValue = ((TestThymeleafView)view).getSomething();
        Assertions.assertEquals("value_from_prototype", viewValue);

        final TestThymeleafView.ViewBean viewBean = ((TestThymeleafView)view).getViewBean();
        Assertions.assertNotNull(viewBean);

        // This should be there, because it is applied through an @Autowired annotation
        final String beanValue = viewBean.getValue();
        Assertions.assertEquals("bean_value", beanValue);

    }


}
