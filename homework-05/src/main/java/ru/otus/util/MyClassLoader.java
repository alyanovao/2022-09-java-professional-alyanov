package ru.otus.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyClassLoader extends ClassLoader {

    public Class<?> defineClass(String className) throws IOException {
        var file = new File(getFileName(className));
        byte[] bytecode = Files.readAllBytes(file.toPath());
        return super.defineClass(className, bytecode, 0, bytecode.length);
    }

    public String getFileName(String className) {
        return "myClass" + File.separator + className.substring(className.lastIndexOf('.') + 1) + ".class";
    }
}
