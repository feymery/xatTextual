
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.concurrent.*
import java.util.*




val port = 4000
public var clients = HashMap<String, Handler>()
public var clientsMap = Collections.synchronizedMap(clients)

@Throws(IOException::class)
fun main(/*args: Array<String>*/){
    
    println("The server is running on port $port")
    var pool = Executors.newFixedThreadPool(500)
    MyServerSocket(port).use { listener ->
        while (true)
            pool.execute(Handler(listener.accept()))
        }
    }
class Handler(clientSocket: MySocket) : Runnable {
    var socket = clientSocket
    var input = BufferedReader(InputStreamReader(socket.getInputStream()))
    var output = PrintWriter(socket.getOutputStream(), true)
    var clientName = ""
    var lastMsg = ""

    override fun run() {
            var username = false
            var logout = false
            while(!username){
                output.print("Enter your username: ")
                output.flush()
                try{
                    clientName = input.readLine()
                    println(clientName)
                }catch(ex: IOException){
                    System.err.println(ex)
                }
                if(!clientsMap.containsKey(clientName)){
                    /*clientsMap.stream().mapValues { ms ->
                        ms.out.print(""">>${clientName} has joined the chat<<""")
                        ms
                    }.forEachOrdered { ms -> ms.out.flush() }*/
                    println(clientName)
                    println("New User: $clientName :)")
                    clientsMap.put(clientName, this)
                    username = true
                }else {
                    output.println("Username already taken :( \n")
                    output.flush()
                }
            }
            while(!logout){
            try{
                if(input.ready()){
                    lastMsg = input.readLine()
                    println("receive message: ${lastMsg} '")
                    if(lastMsg.equals("exit")){
                        clientsMap.remove(clientName)
                        println(clientsMap)
                        /*clientsMap.mapValues { ms ->
                            ms.out.print(""">>${clientName} has left<<""")
                            ms
                        }.forEachOrdered { ms -> ms.out.flush() }*/
                        logout=true
                    }
                }
            }catch(ex: IOException){
                System.err.println(ex)
            }
            if(!"".equals(lastMsg)){
                /*clientsMap.mapValues { ms ->
                if (!ms.clientName.equals(clientName)) { // mirem que no siguem nosaltres
                    ms.out.print("""	>${clientName}: ${lastMsg}""")
                }
                ms
                }.forEachOrdered { ms -> ms.out.flush() }*/
                println("holiii")
            }
            lastMsg=""
        }
        try{
            input.close()
        }catch(ex: IOException){
            System.err.println(ex)
        }
        output.close()
    }
}