package core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun provideHttpClient() :HttpClient{
    return HttpClient(CIO){
        install(DefaultRequest){
            val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiNjY1ZGMwYmM2NWRlMjQ2ZDY1MGJhMTFkIiwiZW1haWwiOiJ0ZXN0MTIzNEBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRlc3QxMjM0In0sImlhdCI6MTcxODAxMTM4NiwiZXhwIjoxNzE4NDQzMzg2fQ.TusfLMR5sVulmwNmvqPnRt9sQNPCPMafDrFjlTgMuM4"
            header("x-auth-token", token)
        }
        install(ContentNegotiation){
            json(Json {
                prettyPrint =true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}