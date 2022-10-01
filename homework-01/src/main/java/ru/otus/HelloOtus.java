package ru.otus;

import com.google.common.base.Functions;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class HelloOtus {
    public static void main(String[] args) {

        List<String> randomStringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomStringList.add(UUID.randomUUID().toString());
        }

        System.out.println(randomStringList);

        Collection<Boolean> transformResultList = Collections2.transform(randomStringList, Functions.forPredicate(Predicates.containsPattern("8|art")));
        System.out.println(transformResultList);
    }
}