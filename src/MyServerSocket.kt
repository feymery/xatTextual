import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.util.logging.Level
import java.util.logging.Logger

class MyServerSocket(port: Int) : ServerSocket(port) {
    val ss = ServerSocket(port)
    @Throws(IOException::class)
    override fun accept(): Socket? {
        try {
            return super.accept()
        } catch (ex: IOException) {
            Logger.getLogger(MyServerSocket::class.java.name).log(Level.SEVERE, null, ex)
        }
        return null
    }
    override fun close() {
        try {
            ss.close()
        } catch (ex: IOException) {
            Logger.getLogger(MyServerSocket::class.java.name).log(Level.SEVERE, null, ex)
        }
    }
}