package com.lsh.list.impl;

import java.util.Arrays;
import java.util.Iterator;

import com.lsh.list.inter.ILCollection;
import com.lsh.list.inter.ILList;

public class LArrayList<T> implements ILList<T> {
//	ArrayList<E>
	private static final int DEFAULT_CAPACITY = 10; 				 // 默认大小
	private static final Object[] EMPTY_DATA = new Object[]{}; //
	
	private int size;
	private Object[] data;
	
	public LArrayList() {
		this.data = EMPTY_DATA;
		size = 0;
	}
	
	public LArrayList(int capacity) {
		if (capacity <= 0) {
			return ;
		}
		
		data = new Object[capacity];
		size = 0;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
			}
		}).start();
		
	}
	
	
	private void ensureCapacityInternal(int size) {
	}
	
	
	/********************* ********************/
	@Override
	public void add(int index, T element) {
		
	}
	
	@Override
	public boolean add(T e) {
		return false;
	}
	@Override
	public boolean addAll(ILCollection<? extends T> c) {
		return false;
	}

	@Override
	public void clear() {
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(ILCollection<? extends T> c) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public boolean remove(Object c) {
		return false;
	}

	@Override
	public boolean removeAll(ILCollection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(ILCollection<?> c) {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean addAll(int index, ILCollection<? extends T> c) {
		return false;
	}

	@Override
	public T get(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public T remove(int index) {
		return null;
	}

	@Override
	public T set(int index, T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILList<T> subList(int fromIndex, int toIndex) {
		return null;
	}
	
	

}
