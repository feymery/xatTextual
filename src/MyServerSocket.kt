import java.io.*
import java.net.*

class MyServerSocket : ServerSocket() {
    override fun accept(): Socket {
        return super.accept()
    }
    override fun close() {
        super.close()
    }
}