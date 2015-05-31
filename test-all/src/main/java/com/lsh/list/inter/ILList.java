package com.lsh.list.inter;

public interface ILList<T> extends ILCollection<T> {
	
	public void add(int index, T element);
	
	public boolean addAll (int index, ILCollection<? extends T> c);
	
	public T get(int index);
	
	public int indexOf(Object o);
	
	public T remove(int index);
	
	public T set(int index, T t);
	
	public ILList<T> subList(int fromIndex, int toIndex);
	
}
