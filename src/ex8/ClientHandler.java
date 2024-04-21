package ex8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread {
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("HH:mm:ss");
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String receivedMsg;
        String toReturn;

        while (true) {
            try {
                dos.writeUTF("What do you want?[Date | Time]..\n"+
                        "Type Exit to terminate connection.");

                receivedMsg = dis.readUTF();

                if (receivedMsg.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                Date date = new Date();
                switch (receivedMsg) {
                    case "Date" :
                        toReturn = fordate.format(date);
                        dos.writeUTF(toReturn);
                        break;

                    case "Time" :
                        toReturn = fortime.format(date);
                        dos.writeUTF(toReturn);
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        try {
            this.dis.close();
            this.dos.close();
        } catch(IOException e) {
            System.out.println("Error closing the sockets: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
