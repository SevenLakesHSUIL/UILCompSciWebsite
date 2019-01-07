package main.data;

import org.springframework.format.Formatter;

import java.util.Locale;

public enum Division {
    NOVICE, ADVANCED;

    public static class DivisionFormatter implements Formatter<Division> {
        @Override
        public Division parse(String text, Locale locale) {
            throw new AssertionError();
//            try {
//                return Division.valueOf(text.toUpperCase());
//            } catch (IllegalArgumentException | NullPointerException e) {
//                throw new ParseException(e.getMessage(), 0);
//            }
        }

        @Override
        public String print(Division div, Locale locale) {
            return div.name().substring(0, 1).concat(div.name().substring(1).toLowerCase());
        }
    }
}