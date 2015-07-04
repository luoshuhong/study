package com.lsh.datastructure.mine;


/**
 * 链表
 * @author Luoshuhong
 * @param <T>
 * @Company  
 * 2015年6月19日
 *
 */
public class LList<T> {
	private Node<T> node;
	
	public LList() {
		node = new Node<T>();
		node.next = null;
	}
	
	public boolean isEmpty() {
		return null == node.next;
	}
	
	/**
	 * 移除某个元素
	 * @param t
	 * @return
	 */
	public T remove(T t) {
		return null;
	}
	
	/**
	 * 添加数据  头插法
	 * @param t
	 */
	public void addHead(T t) {
		Node<T> p = node;
		
		Node<T> temp = new Node<T>();
		temp.data = t;
		
		temp.next = p.next;
		p.next = temp;
		p = temp;
	}
	
	/**
	 * 添加数据  尾插法
	 * @param t
	 */
	public void addTail(T t) {
		Node<T> p = node;
		
		while (null != p.next) {
			p = p.next;
		}
		
		Node<T> temp = new Node<T>();
		temp.data = t;
		p.next = temp;
	}
	
	/**
	 * 打印链表	
	 */
	public void print() {
		Node<T> p = node.next;  //头结点没有数据   直接跳过
		while(null != p) {
			System.out.println(p.data);
			p = p.next;
		}
	}
	
	
	/******************************************/
	class Node<T> {
		T data;		    //数据域
		Node<T> next = null;   //下一节点
	}
	
}
