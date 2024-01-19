package munimul.dsa;

import java.util.Iterator;

import static java.util.Objects.nonNull;

/**
 * @author munimulislam
 * @since 1/19/24
 */
public class DoublyLinkedList<T> {

    int size;

    Node<T> head;

    Node<T> tail;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(T o) {
        return indexOf(o) != -1;
    }

    public int indexOf(T o) {
        Node<T> node = head;
        int index = 0;

        while (node != null) {
            if (node.value == o) {
                return index;
            }

            node = node.next;
            index++;
        }

        return -1;
    }

    public int lastIndexOf(T o) {
        Node<T> node = head;
        int index = 0;
        int lastIndex = -1;

        while (node != null) {
            if (node.value == o) {
                lastIndex = index;
            }

            node = node.next;
            index++;
        }

        return lastIndex;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    public T getFirst() {
        return head.value;
    }

    public T getLast() {
        return tail.value;
    }

    public void set(int index, T o) {
        getNode(index).value = o;
    }

    public void add(T o) {
        if (size() == 0) {
            addFirst(o);
        } else {
            addLast(o);
        }
    }

    public void add(int index, T o) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(o);
            return;
        }

        if (index == size) {
            addLast(o);
            return;
        }

        Node<T> node = getNode(index);

        Node<T> newNode = new Node<>();
        newNode.value = o;
        newNode.next = node;

        if (node != null) {
            newNode.previous = node.previous;
            node.previous.next = newNode;
        }

        size++;
    }

    public void addFirst(T o) {
        Node<T> node = new Node<>();
        node.value = o;

        node.next = head;

        if (size() > 0 && head != null) {
            head.previous = node;
        }

        head = node;
        size++;

        if (size == 1) {
            tail = node;
        }
    }

    public void addLast(T o) {
        Node<T> node = new Node<>();
        node.value = o;

        node.previous = tail;

        if (size() > 0 && tail != null) {
            tail.next = node;
        }

        tail = node;
        size++;

        if (size == 1) {
            head = node;
        }
    }

    public T remove() {
        return removeLast();
    }

    public boolean remove(T o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    public T removeFirst() {
        T value = head.value;

        if(size() > 0 && head.next != null) {
            head.next.previous = null;
        }

        head = head.next;
        size--;

        return value;
    }

    public T removeLast() {
        T value = tail.value;

        if(size() > 0 && tail.previous != null) {
            tail.previous.next = null;
        }

        tail = tail.previous;
        size--;

        return value;
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        Node<T> node = getNode(index);
        node.previous.next = node.next;
        node.next.previous = node.previous;
        size--;

        return node.value;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public DoublyLinkedList<T> reverse() {
        Node<T> node = tail;

        DoublyLinkedList<T> newList = new DoublyLinkedList<>();

        while (node != null) {
            newList.add(node.value);
            node = node.previous;
        }

        return newList;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[ ");
        Node<T> current = head;

        if (nonNull(head)) {
            while (nonNull(current)) {
                if (current == head) {
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
                return index < size();
            }

            @Override
            public T next() {
                return get(index++);
            }
        };
    }

    private Node<T> getNode(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return index >= size / 2 ? getFromTail(index) : getFromHead(index);
    }

    private Node<T> getFromTail(int index) {
        Node<T> node = tail;
        int i = size() - 1;

        while (node != null) {
            if (i == index) {
                return node;
            }

            node = node.previous;
            i--;
        }

        return null;
    }

    private Node<T> getFromHead(int index) {
        Node<T> node = head;
        int i = 0;

        while (node != null) {
            if (i == index) {
                return node;
            }

            node = node.next;
            i++;
        }

        return null;
    }
}

class Node<T> {
    T value;

    Node<T> next;

    Node<T> previous;
}
