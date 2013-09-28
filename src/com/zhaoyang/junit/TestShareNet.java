package com.zhaoyang.junit;

import java.io.File;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhaoyang.dao.NewsDao;
import com.zhaoyang.orm.News;

public class TestShareNet {

	@Test
	public void test() {
		String path = this.getClass().getClassLoader().getResource("")
				.getPath();
		File classDir = new File(path);
		File applicationFile = new File(classDir.getParentFile().getParent()
				+ "\\WebContent\\WEB-INF\\applicationContext.xml");

		ApplicationContext ac = new FileSystemXmlApplicationContext(
				applicationFile.getAbsolutePath());

		NewsDao newsDao = (NewsDao) ac.getBean("newsDao");
		try {
			newsDao.save(new News("asd","asd"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
