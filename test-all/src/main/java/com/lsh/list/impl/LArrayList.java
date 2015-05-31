package com.lsh.list.impl;

import java.util.ArrayList;
import java.util.Iterator;

import com.lsh.list.inter.ILCollection;
import com.lsh.list.inter.ILList;

public class LArrayList<T> implements ILList<T> {
//	ArrayList<E>
	int capacity = 10; // 默认大小
	
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
