package com.example.gambit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author gobeyond
 */
public class BuilderUtils<T> {

    private final Supplier<T> constructor;

    private final List<Consumer<T>> dInjects = new ArrayList<>();

    private BuilderUtils(Supplier<T> constructor) {
        this.constructor = constructor;
    }

    public static <T> BuilderUtils<T> builder(Supplier<T> constructor) {
        return new BuilderUtils<>(constructor);
    }

    public <P> BuilderUtils<T> with(BuilderUtils.DInjectConsumer<T, P> consumer, P p) {
        Consumer<T> c = instance -> consumer.accept(instance, p);
        dInjects.add(c);
        return this;
    }

    public T build() {
        T instance = constructor.get();
        dInjects.forEach(dInject -> dInject.accept(instance));
        return instance;
    }

    @FunctionalInterface
    public interface DInjectConsumer<T, P> {
        void accept(T t, P p);
    }

}
