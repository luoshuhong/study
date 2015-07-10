package com.lsh.spring.aop;

public interface IPointServer {
	public void save(String name);  
    public void update(String name, Integer id);  
    public String getPersonName(Integer id);
}
