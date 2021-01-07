import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors


class Server {
    val port = 4444
    val clientsMap = ConcurrentHashMap<String, Handler>()
    @Throws(IOException::class)
    fun main(args: Array<String>){
        println("The server is running on port $port")
        var pool = Executors.newFixedThreadPool(500)//nidea de pq funciona
        MyServerSocket(port).use { listener ->
            while (true)
                pool.execute(Handler(listener.accept() as MySocket))
            }
        }
}
class Handler : Runnable {
    private var socket: MySocket? = null
    private val lastMsg = ""
    private val clientName: String? = null

    constructor(clientSocket: MySocket) {
        socket = clientSocket
        val input = BufferedReader(InputStreamReader(socket!!.getInputStream()))
        val out = PrintWriter(socket!!.getOutputStream(), true)
    }

    override fun run() {
        /*while (true) {
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
            }*/
    }
}

