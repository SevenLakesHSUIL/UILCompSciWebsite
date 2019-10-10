package main

import main.data.Division
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

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

@Configuration
class StaticResourceConfigurer : WebMvcConfigurer {
    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        super.configurePathMatch(configurer)
        configurer.isUseSuffixPatternMatch = false
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/files/**").addResourceLocations("file:files/")
                .setCachePeriod(0)
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/")
                .setCachePeriod(0)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
