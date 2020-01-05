package io.leiva;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class App {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(1111);
        System.out.println("Initialized...");

        while (true) {
            Socket socket = server.accept();
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String receivedMessage = br.readLine();

            Reflections reflections = new Reflections("io.leiva", new SubTypesScanner(false));
            Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
            for (Class<?> qlass : allClasses) {
                for (Method method : qlass.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(MessageFilter.class)) {
                        String annotationMessage = method.getAnnotation(MessageFilter.class).value();
                        if (annotationMessage.equals(receivedMessage)) {
                            String returnedMessage = (String) method.invoke(qlass.newInstance());
                            returnedMessage += "\n";
                            socket.getOutputStream().write(returnedMessage.getBytes());
                        }
                    }
                }
            }

            socket.close();
        }
    }

}
