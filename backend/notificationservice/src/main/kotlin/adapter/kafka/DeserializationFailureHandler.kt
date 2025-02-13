package adapter.kafka

import io.smallrye.common.annotation.Identifier
import io.smallrye.reactive.messaging.kafka.DeserializationFailureHandler
import jakarta.enterprise.context.ApplicationScoped
import org.apache.kafka.common.header.Headers
import java.lang.Exception

@Identifier("deserialization-failure-fallback")
@ApplicationScoped
class DeserializationFailureHandler: DeserializationFailureHandler<Any> {

    private val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    override fun handleDeserializationFailure(
        topic: String?, isKey: Boolean,
        deserializer: String?, data: ByteArray?,
        exception: Exception?, headers: Headers?
    ): Any? {
        logger.error(
            "Error deserializing on topic {} with deserializer {}", topic, deserializer, exception
        )
        return null
    }
}