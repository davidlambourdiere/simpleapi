package org.lambda.bacasable.simpleapi.core.exception


const val MESSAGE = "Couldn't serialize POJO to JSON"
class SerializationException : RuntimeException(MESSAGE)
