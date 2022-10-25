package ru.otus;


import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    private TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparing(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return new AbstractMap.SimpleEntry<>(
        new Customer(
                map.firstEntry().getKey().getId(),
                map.firstEntry().getKey().getName(),
                map.firstEntry().getKey().getScores()
        ), map.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        for (Map.Entry<Customer, String> e : map.tailMap(customer).entrySet()) {
            if (!e.getKey().equals(customer)) {
                return e;
            }
        }
        return null;
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
