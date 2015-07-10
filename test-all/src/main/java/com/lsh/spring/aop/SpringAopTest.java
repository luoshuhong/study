package com.lsh.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类
 * @author Luoshuhong
 * @Company  
 * 2015年6月30日
 *
 */
public class SpringAopTest {
	@Test  
    public void inteceptorTest(){  
        @SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");  
        IPointServer bean = (IPointServer)ctx.getBean("pointServerImpl");  
        bean.save(null);  
    }
	
}
