package com.gozio.weather.weapose.utils

import com.google.gson.Gson

class ResourceUtil {

    fun <T> getObjectFromJsonResourcesFile(fileName: String, modelClass: Class<T>): T {
        val resource = readJsonResourceAsString(fileName)
        return Gson().fromJson(resource, modelClass)
    }

    private fun readJsonResourceAsString(fileName: String): String {
        return this.javaClass.classLoader?.getResource(fileName)?.readText(Charsets.UTF_8).orEmpty()
    }
}