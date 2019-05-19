package main.data

import org.springframework.format.Formatter

import java.text.ParseException
import java.util.Locale

enum class Division {
    NOVICE, ADVANCED;

    class DivisionFormatter : Formatter<Division> {
        @Throws(ParseException::class)
        override fun parse(text: String, locale: Locale): Division {
            try {
                return valueOf(text.toUpperCase())
            } catch (e: IllegalArgumentException) {
                throw ParseException(e.message, 0)
            } catch (e: NullPointerException) {
                throw ParseException(e.message, 0)
            }
        }

        override fun print(div: Division, locale: Locale): String {
            return div.name.toLowerCase().capitalize()
        }
    }
}