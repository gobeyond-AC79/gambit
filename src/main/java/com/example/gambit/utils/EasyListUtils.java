package com.example.gambit.utils;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * @author gobeyond
 */
public class EasyListUtils {

    public static <T> void forEach(int startIndex, Iterable<? extends T> elements, BiConsumer<Integer, ? super T> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);
        if (startIndex < 0) {
            startIndex = 0;
        }
        int index = 0;
        for (T element : elements) {
            index ++;
            if (index <= startIndex) {
                continue;
            }
            action.accept(index - 1, element);
        }
    }

}
