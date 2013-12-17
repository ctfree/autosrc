package com.hanborq.edrop.msc.atuosrc;

public class MethodInfo {
	private String name;
	private String param;
	private String type;
	public MethodInfo(String name, String param) {
		super();
		this.name = name;
		this.param = param;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "MethodInfo [name=" + name + ", param=" + param + "]";
	}

	
}
