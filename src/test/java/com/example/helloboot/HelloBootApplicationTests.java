package com.example.helloboot;

import com.example.helloboot.springBoot.controller.TestMyStarterController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class HelloBootApplicationTests {

	/**
	 * 用于模拟调用Controller的接口发起请求
	 */
	private MockMvc mvc;

	/**
	 * JUnit中定义在测试用例@Test内容执行前预加载的内容，这里用来初始化对TestMyStarterController对引用
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.standaloneSetup(new TestMyStarterController()).build();
	}

	@Test
	public void contextLoads() throws Exception {
		/**
		 * perform函数执行一次请求调用，accept用于执行接收的数据类型，andExpect用于判断接口返回的期望值
		 */
		mvc.perform(MockMvcRequestBuilders.get("/myStarter")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("charles")));
	}

}
