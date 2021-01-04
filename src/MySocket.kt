import java.io.*
import java.net.*
class MySocket : Socket() {
    override fun connect(endpoint: SocketAddress?) {
        super.connect(endpoint)
    }

    override fun getInputStream(): InputStream {
        return super.getInputStream()
    }

    override fun getOutputStream(): OutputStream {
        return super.getOutputStream()
    }

    override fun close() {
        super.close()
    }
}

