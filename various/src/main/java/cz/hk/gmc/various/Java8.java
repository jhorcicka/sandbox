package cz.hk.gmc.various;

import java.util.Optional;

class Java8 {
    public static void main(String[] args) {
        final Java8 java8 = new Java8();
        java8.put("hello");
        //java8.put(null);
    }

    public void put(final String string) {
        Optional<String> optional = Optional.of(string);
        System.out.println("Input=" + string);
        System.out.println("IsPresent=" + optional.isPresent());
        System.out.println("Get=" + optional.get());
        System.out.println("OrElse=" + optional.orElse("else-alternative"));
        optional.ifPresent((s) -> System.out.println("IfPresent=" + s));
    }
}
