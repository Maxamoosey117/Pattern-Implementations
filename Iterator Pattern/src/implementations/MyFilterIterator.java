package implementations;

import iterators.Iterator;

import java.util.function.Predicate;

public class MyFilterIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private Predicate<T> predicate;
    private T current;

    public MyFilterIterator(Iterator<T> iterator, Predicate<T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        current = null;
    }

    public void first() {
        iterator.first();
        findNext();
    }

    public void next() {
        iterator.next();
        findNext();
    }

    public boolean isDone() {
        return current == null;
    }

    public T current() {
        return current;
    }

    private void findNext() {
        current = null;
        while (!iterator.isDone()) {
            T item = iterator.current();
            if (predicate.test(item)) {
                current = item;
                break;
            }
            iterator.next();
        }
    }
}