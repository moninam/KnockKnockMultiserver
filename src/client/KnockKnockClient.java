package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class KnockKnockClient {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Usar java EchoClient <hostname> <portnumber>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket knockSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(knockSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(knockSocket.getInputStream()));
                ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null ) {
                System.out.println("Server: " + fromServer);

                if (fromServer.equals("Bye.")) {
                    break;
                }
                fromUser = stdIn.readLine();

                if (fromUser != null) {
                    System.out.println("Cliente: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No hay informacion sobre el host "+ hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No puedo obtener I/O para la conexion con "+ hostName);
            System.exit(1);
        }
    }
}
