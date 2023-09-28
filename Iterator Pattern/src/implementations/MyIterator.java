package implementations;

import iterators.IterableSequence;
import iterators.Iterator;

public class MyIterator<T> implements Iterator<T> {
    private IterableSequence<T> sequence;
    private int current;

    public MyIterator(IterableSequence<T> sequence) {
        this.sequence = sequence;
        current = 0;
    }

    public void first() {
        current = 0;
    }

    public void next() {
        current++;
    }

    public boolean isDone() {
        return current >= sequence.size();
    }

    public T current() {
        return sequence.get(current);
    }
}
