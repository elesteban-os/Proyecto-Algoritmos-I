package Sockets;

import java.io.DataOutputStream;
import java.io.IOException;

public class sender {
    private final DataOutputStream output;

    /**
     * Constructor para colocar la salida para los mensajes.
     * @param output permite enviar contenido de un socket
     */
    public sender (DataOutputStream output) {
        this.output = output;
    }

    /**
     * Función que envía un mensaje.
     * @param message texto del mensaje.
     * @throws IOException detecta excepciones de E/S
     */
    public void startSender(String message) throws IOException {
        this.output.writeUTF(message);
    }
}
