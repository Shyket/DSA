package munimul.dsa;

import java.util.Iterator;

/**
 * @author munimulislam
 * @since 1/6/24
 */
public class DynamicArray<T> {

    private int size;

    private int capacity;

    private T[] arr;

    DynamicArray() {
        this(16);
    }

    DynamicArray(int initialCapacity) {
        this.capacity = initialCapacity;
        arr = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int minimumCapacity) {
        if (capacity < minimumCapacity) {
            capacity = minimumCapacity;
            T[] newArr = (T[]) new Object[capacity];

            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }

            arr = newArr;
        }
    }

    public int size() {
        return size;
    }

    public void add(T o) {
        if (capacity <= 0 || size == capacity - 1) {
            expand();
        }

        arr[size++] = o;
    }

    public void add(int index, T o) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (capacity <= 0 || size == capacity - 1) {
            expand();
        }

        size++;

        for (int i = size; i >= index; i--) {
            if (i == index) {
                arr[i] = o;
            } else {
                arr[i] = arr[i - 1];
            }
        }
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return arr[index];
    }

    public boolean contains(T o) {
        return indexOf(o) != -1;
    }

    public int indexOf(T o) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == o) {
                return i;
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        T removed = arr[index];

        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }

        arr[--size] = null;

        if(capacity > size * 2 && capacity > 4) {
            shrink();
        }

        return removed;
    }

    public boolean remove(T o) {
        int index = indexOf(o);

        if(index == -1) {
            return false;
        }else {
            remove(index);
            return true;
        }
    }

    public void set(int index, T o) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        arr[index] = o;
    }

    public void clear() {
        arr = (T[]) new Object[capacity];
        size = 0;
    }

    public Object[] toArray() {
        Object[] newArr = new Object[size];

        for(int i = 0; i < size; i++) {
            newArr[i] = arr[size];
        }

        return newArr;
    }

    private void expand() {
        if(capacity <= 0) {
            ensureCapacity(16);
            return;
        } else {
            capacity *= 2;
        }

        T[] newArr = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }

        arr = newArr;
    }

    private void shrink() {
        capacity = capacity / 4 * 3;

        T[] newArr = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }

        arr = newArr;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");

        for (int i = 0; i < size; i++){
            s.append(" ").append(arr[i]).append(" ");
        }

        s.append("]");

        return s.toString();
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }
}
