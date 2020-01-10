package com.example.samsung.magicianbackend.base;

import org.springframework.data.domain.Sort;

public final class PageRequest {
	private int page = 0;
	private int size = 10;
	private Sort.Direction direction;
	public void setPage(int page) {
		this.page = page < 0 ? 0 :page;
	}
	public void setSize(int size) {
		int DEFAULT_SIZE = 10;
		int MAX_SIZE = 50;
		this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
	}
	public void setDirection(Sort.Direction direction) {
		this.direction = direction;
	}
	
	public org.springframework.data.domain.PageRequest of() {
		return org.springframework.data.domain.PageRequest.of(page, size, Sort.by(direction, "createdAt"));
	}
}
