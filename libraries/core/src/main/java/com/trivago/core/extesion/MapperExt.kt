package com.trivago.core.extesion

fun <Entity, Domain> Entity?.transform(transform: (Entity) -> Domain): Domain? {
    return this?.let { transform.invoke(it) }
}