package org.lambda.bacasable.simpleapi.core.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.jboss.resteasy.links.RESTServiceDiscovery
import org.lambda.bacasable.simpleapi.core.exception.SerializationException

/**
 * Class used to serialize [RESTServiceDiscovery] in JSON with Jackson
 *
 * @author David Lambourdiere
 * */
class RESTServiceDiscoverySerializer: StdSerializer<RESTServiceDiscovery>(RESTServiceDiscovery::class.java) {
    override fun serialize(restServiceDiscovery: RESTServiceDiscovery?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen ?: throw SerializationException()

        gen.writeStartObject()
        restServiceDiscovery?.forEach { atomLink ->
            with(gen) {
                atomLink.apply {
                    writeFieldName(rel)
                    writeStartObject()
                    writePairIfNotNull(HREF.to(href))
                    writePairIfNotNull(TYPE.to(type))
                    writePairIfNotNull(HREFLANG.to(type))
                    writePairIfNotNull(TITLE.to(type))
                    writePairIfNotNull(LENGTH.to(type))
                    writeEndObject()
                }
            }
        }
        gen.writeEndObject()
    }

    /**
     * Write a "key" : "value" mapping to a JSON content if value is not null. Do nothing if not
     * @param pair a key/value pair
     * @see JsonGenerator
     * */
    private fun JsonGenerator.writePairIfNotNull(pair: Pair<String, String>) {
        if (!pair.second.isNullOrEmpty()) {
            writeFieldName(pair.first)
            writeString(pair.second)
        }
    }

    companion object FieldName {
        const val REL = "rel"
        const val HREF = "href";
        const val TYPE = "type";
        const val HREFLANG = "hreflang";
        const val TITLE = "title";
        const val LENGTH = "length";
    }
}

