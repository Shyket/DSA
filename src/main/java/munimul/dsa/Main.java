package munimul.dsa;

import munimul.dsa.LinkedList.LinkedList;

import java.util.*;

/**
 * @author munimulislam
 * @since 1/6/24
 */
public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<String> strs = new DoublyLinkedList<>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");
        strs.add("e");
        strs.add("f");
        strs.remove("b");
        strs.remove("a");
        strs.remove("d");
        strs.remove("e");
        strs.remove("c");
        strs.remove("f");
    }
}