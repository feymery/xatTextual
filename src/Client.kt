import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

    val sPort = 4000        
    val sHost = "Localhost"
    val clientSocket = MySocket(sHost, sPort)
    var teclat = Scanner(System.`in`)
    var output = PrintWriter(clientSocket.getOutputStream(), true)
    val input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
    
    class inputFlow : Runnable {
        override fun run(){
            var line: String = teclat.nextLine()
            while (true) {
                output.print(
                    """
                        $line 
                    """.trimIndent()
                )
                output.flush()
                line = teclat.nextLine()
            }
        }
    }
    class outputFlow : Runnable {
        public override fun run(){      
            try {
                var msg: String = input.readLine()
                while (true) {
                    println(msg)
                    msg = input.readLine()
                }
            }catch (ex: IOException) {
                System.err.println(ex)
            }
        }
    }
    @Throws(IOException::class)
    fun main(){
        val inputFlowThread = Thread(inputFlow())
        val outputFlowThread = Thread(outputFlow())
        inputFlowThread.start()
        outputFlowThread.start()
        println("CLIENT STARTED");
        println("insert logout to close connection and CTRL+D to close chat")
    }