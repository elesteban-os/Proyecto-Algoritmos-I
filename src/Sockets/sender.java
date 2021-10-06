package Sockets;

import java.io.DataOutputStream;
import java.io.IOException;

public class sender {
    private final DataOutputStream output;

    /**
     * @param output permite enviar contenido de un socket
     */
    public sender (DataOutputStream output) {
        this.output = output;
    }

    /**
     * @param message permite enviar un mensaje
     * @throws IOException detecta excepciones de E/S
     */
    public void startSender(String message) throws IOException {
        this.output.writeUTF(message);
    }
}
