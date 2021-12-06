package ru.grobikon.mongoautosequenceexample.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.grobikon.mongoautosequenceexample.entity.Book

interface BookRepository: MongoRepository<Book, Int> {
}