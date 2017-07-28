package aspire.koy.testmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSON;
import aspire.koy.pojo.User;
import aspire.koy.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMybatis {
	
//	private static Logger logger = Logger.getLogger(TestMybatis.class);
	@Autowired
	private IUserService userService = null;
	@Test
	public void test1() {
		User user = userService.getUserById(1);
		System.out.println(user.toString());
//		logger.info(JSON.toJSONString(user));
	}

}
