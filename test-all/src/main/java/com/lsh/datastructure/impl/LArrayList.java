package com.lsh.datastructure.impl;


import java.util.Arrays;

import com.lsh.datastructure.inter.ILCollection;
import com.lsh.datastructure.inter.ILList;

public class LArrayList<T> implements ILList<T> {
	//设置arrayList默认容量
	private static final int DEFAULT_CAPACITY = 10;
	 
	//空数组，当调用无参数构造函数的时候默认给个空数组
	private static final Object[] EMPTY_ELEMENTDATA = {};

	//这是真正保存数据的地方
	private Object[] data;
	
	//实际元素数量
	private int size;      
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	protected transient int modCount = 0;
	
	public LArrayList() {
		this.data = EMPTY_ELEMENTDATA;
		size = 0;
	}
	
	public LArrayList(int capacity) {
		if (capacity <= 0) {
			return ;
		}
		
		data = new Object[capacity];
		size = 0;
	}
	
	/******************** start ********************/
	@Override
	public void add(int index, T element) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		ensureCapacityInternal(size + 1);
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = element;
		size++;
	}
	
	@Override
	public boolean add(T e) {
		ensureCapacityInternal(size + 1);
		data[size++] = e;
		return true;
	}
	@Override
	public boolean addAll(ILCollection<? extends T> c) {
		Object[] arr = c.toArray();
		int arrLen = arr.length;
		ensureCapacityInternal(size + arrLen);
		System.arraycopy(arr, 0, data, size, arrLen);
		size += arrLen;
		return arrLen != 0;
	}

	@Override
	public void clear() {
		modCount ++;
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean remove(Object c) {
		int index = indexOf(c);
		boolean removed = false;
		
		while (-1 != (index = indexOf(c))) {
			System.arraycopy(data, index + 1, data, index, size - index -1);
			data[--size] = null;
			size--;
			removed = true;
		}
		
		return removed;
	}

	@Override
	public boolean removeAll(ILCollection<?> c) {
		boolean removed = false;
		for (int i = 0; i < data.length; i++) {
			if (c.contains(data[i])) {
				remove(data[i]);
				removed = true;
			}
		}
		return removed;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size);
	}

	@Override
	public boolean addAll(int index, ILCollection<? extends T> c) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		Object[] arr = c.toArray();
		int arrLen = arr.length;
		ensureCapacityInternal(size + arrLen);
		
		int indexMod = size - index;
		if (indexMod > 0) {
			System.arraycopy(data, index, data, index + arrLen, arrLen);
		}
		
		System.arraycopy(arr, 0, data, index, arrLen);
		size += arrLen;
		
		return arrLen != 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return (T)data[index];
	}

	@Override
	public int indexOf(Object o) {
		if (null == o) {
			for (int i = 0; i < size; i++) {
				if (null == data[i]) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(data[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public T remove(int index) {
		T t = get(index);
		System.arraycopy(data, index + 1, data, index, size - index -1);
		data[--size] = null;
		return t;
	}

	@Override
	public T set(int index, T t) {
		T oldV = get(index);
		data[index] = t;
		return oldV;
	}

	/****************** end **********************/
	
	
	/**
	 * 检查容量
	 * @param size  现有元素个数
	 */
	private void ensureCapacityInternal(int size) {
		if (this.data == EMPTY_ELEMENTDATA) {
			size = Math.max(DEFAULT_CAPACITY, size);
		}
		
		modCount ++;
		
		//如果现有元素大于容量  则需要扩容了
		if (size - data.length > 0) {
			grow(size);
		}
	}
	
	/**
	 * 扩容 
	 * @param size 现有数据大小
	 */
	private void grow(int size) {
		int oldCapacity = data.length; //原容量
		//设置新数组的容量扩展为原来数组的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		
		 //再判断一下新数组的容量够不够，够了就直接使用这个长度创建新数组  
		 //不够就将数组长度设置为需要的长度
		if (newCapacity - size < 0) {
			newCapacity = size;
		}
		
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			if (size < 0)  { 
				throw new OutOfMemoryError();
			}
			newCapacity = (size > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
		}
		
		//将原来数组的值copy新数组中去， data的引用指向新数组
		//这儿会新创建数组，如果数据量很大，重复的创建的数组，那么还是会影响效率，
		//因此鼓励在合适的时候通过构造方法指定默认的capaticy大小
		data = Arrays.copyOf(data, newCapacity);
	}
	
	
	

}
