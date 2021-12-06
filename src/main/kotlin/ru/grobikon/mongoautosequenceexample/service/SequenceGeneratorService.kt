package ru.grobikon.mongoautosequenceexample.service

import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import ru.grobikon.mongoautosequenceexample.entity.DbSequence

/**
 * Будет выполнять автоматическую генерацию id
 * @param mongoOperations - Интерфейс, определяющий базовый набор операций MongoDB.
 *                          Реализовано MongoTemplate. Не часто используется,
 *                          но полезный вариант для расширяемости и тестируемости
 *                          (поскольку его можно легко высмеять, заглушить или стать
 *                          целью прокси-сервера JDK).
 */
@Service
class SequenceGeneratorService(
    val mongoOperations: MongoOperations
) {

    /**
     * @param sequenceName - название последовательности, для каждой таблицы(документа mongoDB)
     *                       будет иметь своё название, например book_sequence
     */
    fun getSequenceNumber(sequenceName: String): Int {
        //получить порядковый номер
        val query = Query(Criteria.where("id").`is`(sequenceName))
        //обновить порядковый номер
        val update = Update().inc("seq", 1) //seq - ключ последовательность, 1 - увеличивает его на единицу
        //обновляем состояние в документе MongoDB
        //mongoOperations - операции с Mongo
        //findAndModify - найди и измени
        val counter: DbSequence? = mongoOperations
            .findAndModify(
                query,
                update,
                options().returnNew(true).upsert(true),
                DbSequence::class.java)
        return counter?.seq?:1
    }
}