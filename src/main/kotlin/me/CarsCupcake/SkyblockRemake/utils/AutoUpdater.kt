package me.CarsCupcake.SkyblockRemake.utils

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import me.CarsCupcake.SkyblockRemake.Main
import org.bukkit.Bukkit

// Original Code from Skytils, modiefied by CarsCupcake
object AutoUpdater {
    val URL = "https://api.github.com/repos/CarsCupcake/SkyblockRemake"
    val IO = object : CoroutineScope {
        override val coroutineContext = Dispatchers.IO + SupervisorJob() + CoroutineName("Skytils IO")
    }
    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            include(serializersModule)
            contextual(Regex::class, RegexAsString)
        }
    }
    val client = HttpClient(CIO) {
        install(ContentEncoding) {
            deflate(1.0F)
            gzip(0.9F)
        }
        install(ContentNegotiation) {
            json(json)
        }
        install(HttpCache)
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
            exponentialDelay()
        }
        install(UserAgent) {
            agent = "SkyblockRemake/${Main.VERSION}"
        }

        engine {
            endpoint {
                connectTimeout = 10000
                keepAliveTime = 5000
                requestTimeout = 10000
                socketTimeout = 10000
            }
        }
    }
    fun check() {
        IO.launch {
            println("Checking Version...")
            val release = client.get(urlString = "$URL/releases/latest").body<GithubRelease>()
            if(isHigher(release.tagName)){
                Bukkit.broadcastMessage("§aThere is a new release: ${release.tagName}")
            }else
                println("You have the latest build!")
        }
    }
    private fun isHigher(versionString: String): Boolean{
        val subs = versionString.replace("v", "").split(".")
        val comparator = Main.VERSION.split(".")
        for ((i, s) in subs.withIndex()){
            if(Integer.parseInt(s) > Integer.parseInt(comparator[i]))
                return true
        }
        return false
    }
}