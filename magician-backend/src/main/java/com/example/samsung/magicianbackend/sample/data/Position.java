package com.example.samsung.magicianbackend.sample.data;

public enum Position {
	CL1("사원"),CL2("대리"),CL3("과장"),CL4("부장");
	
	final private String name;
	
	private Position(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
