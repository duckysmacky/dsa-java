package io.github.duckysmacky.dsa;

import io.github.duckysmacky.dsa.data.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>("a", "b", "c");
        list.append("aa");
        list.append("bb");
        list.addAt(3, "cc");
        list.setAt(1, "??s");
        list.removeEnd();
        list.append("!!");
        list.setAt(3, "*");

        System.out.println(list);
    }
}