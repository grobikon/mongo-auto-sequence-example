package ru.grobikon.mongoautosequenceexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongoAutoSequenceExampleApplication

fun main(args: Array<String>) {
    runApplication<MongoAutoSequenceExampleApplication>(*args)
}
