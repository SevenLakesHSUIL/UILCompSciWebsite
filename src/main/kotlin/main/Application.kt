package main

import main.data.Division
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
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

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }
}