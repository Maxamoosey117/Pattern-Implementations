package implementations;

import iterators.Iterator;

import java.util.Stack;

public class MyReverseIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private Stack<T> stack;

    public MyReverseIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        stack = new Stack<>();
    }

    public void first() {
        iterator.first();
        while (!iterator.isDone()) {
            stack.push(iterator.current());
            iterator.next();
        }
    }

    public void next() {
        stack.pop();
    }

    public boolean isDone() {
        return stack.isEmpty();
    }

    public T current() {
        return stack.peek();
    }
}
