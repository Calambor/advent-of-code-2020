package support

import java.io.File
import java.net.HttpURLConnection
import java.net.URL


public fun downloadInput(day: Int, testCase: Int? = null): List<String> {
    val fileName = if (testCase == null) {
        "input${day}"
    } else {
        "input${day}_${testCase}"
    }
    val file = File(fileName)
    if (!file.exists() && testCase == null) {
        with(URL("https://adventofcode.com/2020/day/$day/input").openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            val cookie = File("credentialsCookie").readLines().first()
            setRequestProperty("Cookie", cookie)

            file.createNewFile()
            inputStream.copyTo(file.outputStream())
        }
    }

    return file.readLines()
}
