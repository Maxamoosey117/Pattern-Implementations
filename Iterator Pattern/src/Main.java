import implementations.*;
import iterators.*;

public class Main {
    public static void main(String[] args) {
    /*
    Write a driver that shows your FilterIterator in action:
1.	With two different types of elements
2.	A FilterIterator that filters results from another FilterIterator
3.	A FilterIterator that filters out everything
4.	A ReverseIterator
5.	A ReverseIterator that takes a FilterIterator, and thus outputs the results of the Filter in reverse order

     */

        //1 - With two different types of elements
        MyArray<Integer> array = new MyArray<>(10);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(7);
        array.add(8);
        array.add(9);
        array.add(10);

        MyArray<String> array2 = new MyArray<>(10);
        array2.add("apple");
        array2.add("banana");
        array2.add("carrot");
        array2.add("date");
        array2.add("eggplant");
        array2.add("fig");

        MyFilterIterator<Integer> filter = new MyFilterIterator<>(array.getIterator(), (Integer i) -> i % 2 == 0);

        MyFilterIterator<String> filter2 = new MyFilterIterator<>(array2.getIterator(), (String s) -> s.length() > 4);

        System.out.println("Even numbers:");
        for(filter.first(); !filter.isDone(); filter.next()) {
            System.out.println(filter.current());
        }

        System.out.println("Strings longer than 4 characters:");
        for(filter2.first(); !filter2.isDone(); filter2.next()) {
            System.out.println(filter2.current());
        }


        //2 - A FilterIterator that filters results from another FilterIterator
        MyFilterIterator<Integer> filter3 = new MyFilterIterator<>(filter, (Integer i) -> i % 3 == 0);

        System.out.println("Even numbers divisible by 3:");
        for(filter3.first(); !filter3.isDone(); filter3.next()) {
            System.out.println(filter3.current());
        }

        //3 - A FilterIterator that filters out everything
        MyFilterIterator<Integer> filter4 = new MyFilterIterator<>(array.getIterator(), (Integer i) -> false);

        System.out.println("Filter that filters out everything:");
        for(filter4.first(); !filter4.isDone(); filter4.next()) {
            System.out.println(filter4.current());
        }

        //4 - A ReverseIterator
        MyReverseIterator<Integer> reverse = new MyReverseIterator<>(array.getIterator());

        System.out.println("Reverse Iterator:");
        for(reverse.first(); !reverse.isDone(); reverse.next()) {
            System.out.println(reverse.current());
        }

        //5 - A ReverseIterator that takes a FilterIterator, and thus outputs the results of the Filter in reverse order
        MyReverseIterator<Integer> reverse2 = new MyReverseIterator<>(filter);

        System.out.println("Reverse Iterator that takes a FilterIterator:");
        for(reverse2.first(); !reverse2.isDone(); reverse2.next()) {
            System.out.println(reverse2.current());
        }

    }
}
