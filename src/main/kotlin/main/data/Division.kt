package main.data

import org.springframework.format.Formatter

import java.util.Locale

enum class Division {
    NOVICE, ADVANCED;

    class DivisionFormatter : Formatter<Division> {
        override fun parse(text: String, locale: Locale?): Division =
            valueOf(text.toUpperCase())

        override fun print(div: Division, locale: Locale?): String =
            div.name.toLowerCase().capitalize()
    }
}
