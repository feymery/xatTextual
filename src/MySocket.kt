import java.io.IOException
import java.io.InputStream
import java.io.*
import java.net.Socket
import java.net.SocketAddress


class MySocket: Socket{
    var clientSocket: Socket?
    init{
        clientSocket=null
    }

    constructor(host: String, port: Int){
        try {
            clientSocket = Socket(host, port)
        } catch (ex: IOException) {
            System.err.println(ex)
        }
    }
    constructor(socket: Socket){
        clientSocket = socket
    }


    override fun connect(endpoint: SocketAddress) {
        try {
            clientSocket!!.connect(endpoint)
        } catch (ex: IOException) {
            System.err.println(ex)
        }
    }

    override fun getInputStream(): InputStream? {
        try {
            return clientSocket!!.getInputStream()
        } catch (ex: IOException) {
            System.err.println(ex)
        }
        return null

    }

    override fun getOutputStream(): OutputStream? {
        try {
            return clientSocket!!.getOutputStream()
        } catch (ex: IOException) {
            System.err.println(ex)
        }
        return null
    }

    override fun close() {
        try {
            clientSocket!!.close()
        } catch (ex: IOException) {
            System.err.println(ex)
        }
    }
}