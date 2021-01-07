import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*


class client {
    val sPort = 4000
    val sHost = "Localhost"

    @Throws(IOException::class)
    fun main(args: Array<String>){
        val clientSocket = MySocket(sHost, sPort)
        var teclat = Scanner(System.`in`)
        var out = PrintWriter(clientSocket.getOutputStream(), true)
        val input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
        val inputFlow = Thread {
            Runnable {
                fun run(){
                    var line: String = teclat.nextLine()
                    while (line != null) {
                        out.print(
                            """
                                $line
            
                            """.trimIndent()
                        )
                        out.flush()
                        line = teclat.nextLine()
                    }
                }
            }
        }
        val outputFlow = Thread {
            Runnable {
                fun run(){
                    try {
                        var msg: String = input.readLine()
                        while (msg != null) {
                            println(msg)
                            msg = input.readLine()
                        }
                    } catch (ex: IOException) {
                        System.err.println(ex)
                    }
                }
            }
        }
        inputFlow.start()
        outputFlow.start()
        println("CLIENT STARTED");
        println("insert logout to close connection and CTRL+D to close chat")
    }
}