import com.sun.security.ntlm.Client
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger


class client {
    @Throws(IOException::class)
    fun main(args: Array<String>){
        val clientSocket = MySocket("localhost", 4444)
        var name: String? = "initial"
        var lastLine: String? = "initial"
        var teclat = Scanner(System.`in`)
        var out = PrintWriter(clientSocket.getOutputStream(), true)
        val input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
        val inputFlow = Thread {
            var linia: String
            while (teclat.nextLine().also { linia = it } != null) {
                lastLine = linia
                out.print(
                    """
                          $linia
                          
                          """.trimIndent()
                )
                out.flush()
            }
        }
        val outputFlow = Thread {
            try {
                var msg: String = "msg"
                while (input.readLine().also { msg = it } != null) {
                    if (lastLine.toString() in msg && msg.contains("joined")) {
                        name = lastLine
                        System.out.println(name)
                    }
                    if (msg !== "null") println(msg)
                }
            } catch (ex: IOException) {
                Logger.getLogger(Client::class.java.name).log(Level.SEVERE, null, ex)
            }
        }
        inputFlow.start()
        outputFlow.start()
        println("CLIENT STARTED");
        println("insert logout to close connection and CTRL+D to close chat")
    }
/*// Input Thread
while ((línia = in.readLine()) != null)
escriure línia per socket;

// Output Thread
while (hi ha línia del servidor)
escriure línia per pantalla;*/


//create socket with class my socket

}