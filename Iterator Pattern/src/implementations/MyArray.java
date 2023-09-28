package implementations;

import iterators.IterableSequence;
import iterators.Iterator;

public class MyArray<T> implements IterableSequence<T> {
    private T[] array;
    private int size;

    public MyArray(int capacity) {
        array = (T[]) new Object[capacity];
        size = 0;
    }

    public void add(T value) {
        if (size < array.length) {
            array[size++] = value;
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return array.length;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public Iterator<T> getIterator() {
        return new MyIterator<T>(this);
    }
}
