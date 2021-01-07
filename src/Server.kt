import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.thread


class Server {
    @Throws(IOException::class)
    fun main(args: Array<String>){
        val serverSocket = MyServerSocket(Integer.parseInt(args[0]))
        println("The server is running...")
        val users = Collections.synchronizedMap(HashMap<String, PrintWriter>())
        while (true) {
            val s = serverSocket.accept()
            thread {
                var sin = s?.getInputStream()?.bufferedReader()
                val sout = PrintWriter(s?.getOutputStream(), true)

                // read nick
                val nick = sin?.readLine()
                users.put(nick, sout)

                if (sin != null) {
                    sin.forEachLine {
                        for (peer in users.values) {
                            if (peer == sout) continue
                            peer.println(it)
                        }
                    }
                }
                users.remove(nick)
            }
        }

    }

}
class Handler : Runnable {
    val socket = MySocket()
    override fun run() {
        try(socket)
    }

}

