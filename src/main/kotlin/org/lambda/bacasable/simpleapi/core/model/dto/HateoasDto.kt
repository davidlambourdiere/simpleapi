package org.lambda.bacasable.simpleapi.core.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.jboss.resteasy.links.RESTServiceDiscovery
import org.lambda.bacasable.simpleapi.core.serializer.RESTServiceDiscoverySerializer

open class HateoasDto(
    @get:JsonProperty("_links")
    @get:JsonSerialize(using = RESTServiceDiscoverySerializer::class)
    var restServiceDiscovery: RESTServiceDiscovery? = null)
