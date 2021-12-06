package ru.grobikon.mongoautosequenceexample.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "books")
data class Book(

    @Id
    var id: Int? = null,
    var name: String? = null,
    var price: Double? = null
) {

    companion object {
        //@Transient используется, чтобы указать, что поле не сохраняется в базе данных
        @Transient
        const val SEQUENCE_NAME = "book_sequence"
    }
}
