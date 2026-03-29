package feature.simpleproject.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class SimpleProjectInfo(
    val id: String,
) {
    companion object {
        @OptIn(ExperimentalUuidApi::class)
        fun createRandom(): SimpleProjectInfo {
            return SimpleProjectInfo(
                id = Uuid.random().toString(),
            )
        }
    }
}