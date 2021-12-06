package ru.grobikon.mongoautosequenceexample.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Таблица для создания автоматического идентификатора. В поле ID.
 */
@Document(collection = "db_sequence")
data class DbSequence(
    @Id
    var id: String,
    var seqNo: Int
)
