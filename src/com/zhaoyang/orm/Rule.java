package com.zhaoyang.orm;
import java.util.Date;

public class Rule implements java.io.Serializable {
	
	private Long id;
	private String ruleId;
	private String ruleDef;
	private String ruleType;
	
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleDef() {
		return ruleDef;
	}
	public void setRuleDef(String ruleDef) {
		this.ruleDef = ruleDef;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}