import java.io.*
import java.net.*

class MyServerSocket(port: Int) : ServerSocket() {
    var serverSocket = ServerSocket()
    lateinit var clientSocket: MySocket

    init {
        serverSocket = ServerSocket(port)
    }

    @Throws(IOException::class)
    override fun accept(): Socket? {
        try {
            clientSocket = MySocket(serverSocket.accept())
        } catch (ex: IOException) {
            System.err.println(ex)
        }
        return null
    }
    override fun close() {
        try {
            serverSocket.close()
        } catch (ex: IOException) {
            System.err.println(ex)
        }
    }
}