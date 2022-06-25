package org.lambda.hateoas.core.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.jboss.resteasy.links.RESTServiceDiscovery

open class HateoasDto(
    @get:JsonProperty("_links")
    var restServiceDiscovery: RESTServiceDiscovery? = null)
