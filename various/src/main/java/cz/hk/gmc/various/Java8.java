package cz.hk.gmc.various;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Java8 {
    public static void main(String[] args) {
        final Java8 java8 = new Java8();
        java8.streams();

        java8.optional("hello");
        //java8.optional(null);
    }

    public void streams() {
        final List<String> list = new ArrayList<>();
        list.add("aaa1");
        list.add("bbb1");
        list.add("ccc1");
        list.add("aaa2");
        list.add("bbb2");
        list.add("ccc2");

        list.stream().filter((s) -> s.startsWith("b")).forEach(System.out::println);
    }

    public void optional(final String string) {
        Optional<String> optional = Optional.of(string);
        System.out.println("Input=" + string);
        System.out.println("IsPresent=" + optional.isPresent());
        System.out.println("Get=" + optional.get());
        System.out.println("OrElse=" + optional.orElse("else-alternative"));
        optional.ifPresent((s) -> System.out.println("IfPresent=" + s));
    }
}
