import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.net.SocketAddress
import java.util.logging.Level
import java.util.logging.Logger


class MySocket: Socket{
    lateinit var sc : Socket

    constructor(host: String, port: Int){
        try {
            sc = Socket(host, port)
        } catch (ex: IOException) {
            Logger.getLogger(MySocket::class.java.name).log(Level.SEVERE, null, ex)
        }
    }
    constructor(socket: Socket){
        sc = socket
    }


    override fun connect(endpoint: SocketAddress?) {
        try {
            this.sc.connect(endpoint)
        } catch (ex: IOException) {
            Logger.getLogger(MySocket::class.java.name).log(Level.SEVERE, null, ex)
        }
    }

    override fun getInputStream(): InputStream? {
        try {
            return sc.getInputStream()
        } catch (ex: IOException) {
            Logger.getLogger(MySocket::class.java.name).log(Level.SEVERE, null, ex)
        }
        return null
    }

    override fun getOutputStream(): OutputStream? {
        try {
            return sc.getOutputStream()
        } catch (ex: IOException) {
            Logger.getLogger(MySocket::class.java.name).log(Level.SEVERE, null, ex)
        }
        return null
    }

    override fun close() {
        try {
            sc.close()
        } catch (ex: IOException) {
            Logger.getLogger(MySocket::class.java.name).log(Level.SEVERE, null, ex)
        }
    }
}

