package main

import main.data.Division
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
@Controller
class Application {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/downloads")
    fun downloads(): String {
        return "downloads"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @Bean
    internal fun divisionFormatter(): Division.DivisionFormatter {
        return Division.DivisionFormatter()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}