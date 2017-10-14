package jehc.xtmodules.xtcore.proxy.queuebox;
import java.util.Collection;  
import java.util.Iterator;  
import java.util.LinkedList;  
import java.util.Queue;  
/**
 * LimitQueue列队
 * @author 邓纯杰
 *
 * @param <E>
 */
public class LimitQueue<E> implements Queue<E> {  
    private int limit;  
    private Queue<E> queue;  
  
    public LimitQueue(int limit) {  
        this.limit = limit;  
        this.queue = new LinkedList<E>();  
    }  
  
    public int size() {  
        return queue.size();  
    }  
  
    public boolean isEmpty() {  
        return queue.isEmpty();  
    }  
  
    public boolean contains(Object o) {  
        return queue.contains(o);  
    }  
  
    public Iterator<E> iterator() {  
        return queue.iterator();  
    }  
  
    public Object[] toArray() {  
        return queue.toArray();  
    }  
  
    public <T> T[] toArray(T[] a) {  
        return queue.toArray(a);  
    }  
  
    public boolean add(E e) {  
        return queue.add(e);  
    }  
  
    public boolean remove(Object o) {  
        return queue.remove(0);  
    }  
  
    public boolean containsAll(Collection<?> c) {  
        return queue.containsAll(c);  
    }  
  
    public boolean addAll(Collection<? extends E> c) {  
        return queue.addAll(c);  
    }  
  
    public boolean removeAll(Collection<?> c) {  
        return queue.removeAll(c);  
    }  
  
    public boolean retainAll(Collection<?> c) {  
        return queue.retainAll(c);  
    }  
  
    public void clear() {  
        queue.clear();  
    }  
  
    public boolean offer(E e) {  
        if (queue.size() >= limit) {  
            queue.poll();  
        }  
        return queue.offer(e);  
    }  
  
    public E remove() {  
        return queue.remove();  
    }  
  
    public E poll() {  
        return queue.poll();  
    }  
  
    public E element() {  
        return queue.element();  
    }  
  
    public E peek() {  
        return queue.peek();  
    }  
  
    public int getLimit() {  
        return this.limit;  
    }  
}  