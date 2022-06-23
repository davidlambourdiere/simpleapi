package org.lambda.hateoas.core.config.mapper

import org.mapstruct.InjectionStrategy
import org.mapstruct.MapperConfig
import org.mapstruct.ReportingPolicy

@MapperConfig(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CustomMapperConfig
