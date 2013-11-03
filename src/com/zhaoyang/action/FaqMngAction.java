package com.zhaoyang.action;

import java.util.List;

import com.zhaoyang.dao.FaqDao;
import com.zhaoyang.data.Faq;

public class FaqMngAction extends AbstractActionSupport {
	private FaqDao faqDao = new FaqDao();

	public FaqDao getFaqDao() {
		return faqDao;
	}

	public void setFaqDao(FaqDao faqDao) {
		this.faqDao = faqDao;
	}

	private List<Faq> faqs;
	

	public List<Faq> getFaqs() {
		return faqs;
	}

	public void setFaqs(List<Faq> faqs) {
		this.faqs = faqs;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		faqs = faqDao.findAll();
		return SUCCESS;
	}

}
