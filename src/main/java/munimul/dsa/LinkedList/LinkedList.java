package munimul.dsa.LinkedList;

import java.util.Iterator;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * @author munimulislam
 * @since 1/6/24
 */
public class LinkedList<T> {

    Node<T> head;

    int size;

    public void add(T o) {
        if (size == 0) {
            addFirst(o);
        } else {
            addLast(o);
        }
    }

    public void add(int index, T o) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            addFirst(o);
        } else {
            Node current = head;
            int i = 0;

            while (nonNull(current)) {
                if (i + 1 == index) {
                    Node<T> n = new Node<>();
                    n.value = o;
                    n.next = current.next;
                    current.next = n;
                    break;
                }

                current = current.next;
                i++;
            }

            size++;
        }
    }

    public void addFirst(T o) {
        Node<T> n = new Node<T>();
        n.value = o;
        n.next = head;
        head = n;
        size++;
    }

    public void addLast(T o) {
        add(size, o);
    }

    public T get(int index) {
        return Objects.requireNonNull(getNode(index)).value;
    }

    public T getFirst() {
        return (T) head.value;
    }

    public T getLast() {
        return Objects.requireNonNull(getNode(size-1)).value;
    }

    public T remove() {
       return removeLast();
    }

    public T removeFirst() {
        if(size == 0) {
            throw new IndexOutOfBoundsException();
        }

        T value = head.value;
        head = head.next;
        size--;

        return value;
    }

    public T removeLast() {
        return remove(size - 1);
    }

    public T remove(int index) {
        if(size == 0  || index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        T value = null;

        if(index == 0) {
            value = removeFirst();
        } else {
            int i = 0;
            Node<T> temp = head;

            while (nonNull(temp)) {
                if (i + 1 == index) {
                    value = temp.next.value;
                    temp.next = temp.next.next;
                    break;
                }

                i++;
                temp = temp.next;
            }

            size--;
        }

        return value;
    }

    public boolean remove(T o) {
        int index = indexOf(o);

        if(index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    public void set(int index, T o) {
        int i = 0;
        Node<T> temp = head.next;

        while(nonNull(temp)) {
            if(i == index) {
                temp.value = o;
                break;
            }

            i++;
            temp = temp.next;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int indexOf(Object o) {
        int i = 0;
        Node<T> temp = head;

        while(nonNull(temp)) {
            if(temp.value == o) {
                return i;
            }

            i++;
            temp = temp.next;
        }

        return -1;
    }

    public int lastIndexOf(Object o) {
        int i = 0;
        int lastIndex = -1;
        Node<T> temp = head;

        while(nonNull(temp)) {
            if(temp.value.equals(o)) {
                lastIndex = i;
            }

            i++;
            temp = temp.next;
        }

        return lastIndex;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[ ");
        Node<T> current = head;

        if(nonNull(head)) {
            while (nonNull(current)) {
                if(current == head) {
                    s.append(current.value);
                } else {
                    s.append(", ").append(current.value);
                }

                current = current.next;
            }
        }

        return s.append(" ]").toString();
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return get(index++);
            }
        };
    }

    private Node<T> getNode(int index) {
        if(size < index) {
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        Node<T> temp = head;

        while(nonNull(temp)) {
            if(i == index) {
                return temp;
            }

            i++;
            temp = temp.next;
        }

        return null;
    }
}


class Node<T> {

    T value;

    Node<T> next;
}
