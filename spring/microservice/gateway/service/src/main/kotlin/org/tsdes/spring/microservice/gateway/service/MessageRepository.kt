package org.tsdes.spring.microservice.gateway.service

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface MessageRepository : CrudRepository<MessageEntity, Long>
