package ru.grobikon.mongoautosequenceexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.grobikon.mongoautosequenceexample.entity.Book
import ru.grobikon.mongoautosequenceexample.repository.BookRepository
import ru.grobikon.mongoautosequenceexample.service.SequenceGeneratorService

@SpringBootApplication
@RestController
class MongoAutoSequenceExampleApplication(
    val bookRepository: BookRepository,
    val sequenceGeneratorService: SequenceGeneratorService
) {

    @PostMapping("/saveBook")
    fun saveBook(@RequestBody book: Book): Book {
        //generate sequence
        book.id = sequenceGeneratorService.getSequenceNumber(Book.SEQUENCE_NAME)
        return bookRepository.save(book)
    }

    @GetMapping("/books")
    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }
}

fun main(args: Array<String>) {
    runApplication<MongoAutoSequenceExampleApplication>(*args)
}
