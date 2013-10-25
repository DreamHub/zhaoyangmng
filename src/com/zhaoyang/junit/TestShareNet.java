package com.zhaoyang.junit;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhaoyang.action.PagesGenerateAction;
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

		PagesGenerateAction pagesGenerateAction = (PagesGenerateAction) ac
				.getBean("pagesGenerateAction");
		try {
			// newsDao.save(new News("asd","asd"));
			pagesGenerateAction.generateNewsHTML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testClassSave() {
		Configuration configuration = new Configuration();
		SessionFactory factory = configuration.configure().buildSessionFactory();
//		Session session = factory.openSession();
		
	}

}
