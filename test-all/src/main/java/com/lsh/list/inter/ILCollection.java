package com.lsh.list.inter;

import java.util.Iterator;



/**
 * LList/LSet 父接口
 * @author Luoshuhong
 * @Company 
 * 2015年5月31日
 *
 */
public interface ILCollection<T> extends Iterable<T>{
	
	public boolean add(T e);
	
	public boolean addAll(ILCollection<? extends T> c);
	
	public void clear();
	
	public boolean contains(Object o);
	
	public boolean containsAll (ILCollection<? extends T> c);
	
	public boolean equals(Object o);
	
	public int hashCode();
	
	public boolean isEmpty();
	
	public Iterator<T> iterator();
	
	public boolean remove(Object c);
	
	public boolean removeAll(ILCollection<?> c);
	
	public boolean retainAll(ILCollection<?> c);
	
	public int size();
	
	public Object[] toArray();
	
	public <T> T[] toArray(T[] a);
}
