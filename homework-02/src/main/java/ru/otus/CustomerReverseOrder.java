package ru.otus;


import java.util.*;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    Deque<Customer> map = new LinkedList<>();

    public void add(Customer customer) {
        map.addFirst(customer);
    }

    public Customer take() {
        return map.poll();
    }
}
