package jehc.xtmodules.xtcore.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SyncMessageQueue<E> {

	// 私有队列
	private E[] queue;

	// 队列容量，即最大值
	private volatile int capacity;

	// 队列含有对象的数目
	private volatile int size;

	// 队列头指针
	private volatile int head;

	// 队列尾指针
	private volatile int tail;

	@SuppressWarnings("unchecked")
	public SyncMessageQueue(int cap) {
		capacity = (cap > 0) ? cap : 1;
		queue = (E[])new Object[capacity];
		size = 0;
		head = 0;
		tail = 0;
	}

	public int getSize() {
		return size;
	}

	public synchronized boolean isFull() {
		return (size == capacity);
	}

	public synchronized void add(E obj) throws InterruptedException {
		if(obj == null) return;
		
		while(size == capacity) {
			wait();
		}
		
		queue[head] = obj;
		head = (head + 1) % capacity;
		size++;

		notify();
	}

	public synchronized E remove() throws InterruptedException {
		while(size == 0) {
			wait();
		}

		E obj = queue[tail];
		queue[tail] = null;
		tail = (tail + 1) % capacity;
		size--;

		notify();

		return obj;
	}

	
	public synchronized void add(List<E> list) throws InterruptedException {
		//System.out.println(Thread.currentThread().getName() + "--向队列放入元素[" + list.size() + "]个");
		//System.out.println(Thread.currentThread().getName() + "--队列的容量是[" + capacity + "]个");
		//System.out.println(Thread.currentThread().getName() + "--队列现在的大小是[" + size + "]个");
		while(size == capacity) {
			System.out.println(Thread.currentThread().getName() + "--队列超出最大限制！等待唤醒");
			wait();
		}
		//System.out.println(Thread.currentThread().getName() + "--加数据被唤醒！");
		
		Iterator<E> itr = list.iterator();
		while(itr.hasNext()) {
			while(size == capacity) {
				System.out.println(Thread.currentThread().getName() + "--队列超出最大限制！等待唤醒" + list.size());
				wait();
			}
			E obj = itr.next();
			itr.remove();
			if(obj == null) continue;
			queue[head] = obj;
			head = (head + 1) % capacity;
			size++;
		}
		//System.out.println(Thread.currentThread().getName() + "--加入数据结束，队列现在的大小是[" + size + "]个");

		notify();		
	}

	public synchronized List<E> remove(int limit) throws InterruptedException {
		//System.out.println(Thread.currentThread().getName() + "--准备取走队列元素[" + limit + "]个");
		while(size == 0) {
			//System.out.println(Thread.currentThread().getName() + "--队列为空无法取数据, 等待唤醒!");			
			wait();
		}
		//System.out.println(Thread.currentThread().getName() + "--取数据被唤醒！");
		
		if (limit > size || limit <= 0)
			limit = size;
		
		LinkedList<E> list = new LinkedList<E>();

		for (int i = 0; i < limit; i++) {
			E obj = queue[tail];
			queue[tail] = null;
			tail = (tail + 1) % capacity;
			size--;
			list.add(obj);
		}

		notify();
		
		//System.out.println(Thread.currentThread().getName() + "--取走队列元素[" + list.size() + "]个");		
		
		return list;
	}
	
	public synchronized List<E> addNoBlock(List<E> list) {
		//System.out.println(Thread.currentThread().getName() + "--向队列放入元素[" + list.size() + "]个");
		//System.out.println(Thread.currentThread().getName() + "--队列的容量是[" + capacity + "]个");
		//System.out.println(Thread.currentThread().getName() + "--队列现在的大小是[" + size + "]个");
		if(size == capacity) {
			System.out.println(Thread.currentThread().getName() + "--队列超出最大限制！");
			return list;
		}
		//System.out.println(Thread.currentThread().getName() + "--加数据被唤醒！");
		
		Iterator<E> itr = list.iterator();
		while(itr.hasNext()) {
			if(size == capacity) {
				System.out.println(Thread.currentThread().getName() + "--队列超出最大限制！");
				return list;
			}
			E obj = itr.next();
			itr.remove();
			if(obj == null) continue;
			queue[head] = obj;
			head = (head + 1) % capacity;
			size++;
		}
		notify();		
		//System.out.println(Thread.currentThread().getName() + "--加入数据结束，队列现在的大小是[" + size + "]个");
		return null;
	}

	public synchronized List<E> removeNoBlock(int limit) {
		//System.out.println(Thread.currentThread().getName() + "--准备取走队列元素[" + limit + "]个");
		if(size == 0) {
			//System.out.println(Thread.currentThread().getName() + "--队列为空无法取数据");			
			return null;
		}
		//System.out.println(Thread.currentThread().getName() + "--取数据被唤醒！");
		
		if (limit > size || limit <= 0)
			limit = size;
		
		LinkedList<E> list = new LinkedList<E>();

		for (int i = 0; i < limit; i++) {
			E obj = queue[tail];
			queue[tail] = null;
			tail = (tail + 1) % capacity;
			size--;
			list.add(obj);
		}
		
		notify();
		//System.out.println(Thread.currentThread().getName() + "--取走队列元素[" + list.size() + "]个");				
		return list;
	}

}