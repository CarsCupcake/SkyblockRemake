package me.CarsCupcake.SkyblockRemake.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
// Original Code from Skytils
@Serializable
data class GithubRelease(
    @SerialName("tag_name")
    val tagName: String,
    val assets: List<GithubAsset>,
    val body: String
)

@Serializable
data class GithubAsset(
    val name: String,
    @SerialName("browser_download_url")
    val downloadUrl: String,
    val size: Long,
    @SerialName("download_count")
    val downloadCount: Long,
    val uploader: GithubUser
)

@Serializable
data class GithubUser(
    @SerialName("login")
    val username: String
)

object RegexAsString : KSerializer<Regex> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Regex", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): Regex = Regex(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: Regex) = encoder.encodeString(value.pattern)
}