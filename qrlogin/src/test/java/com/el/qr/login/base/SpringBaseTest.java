//
// Powered By [rapid-generator-framework]
// (By:qinxf)
//

package com.el.qr.login.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml"})
public class SpringBaseTest extends AbstractJUnit4SpringContextTests {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBaseTest.class);


	
}
