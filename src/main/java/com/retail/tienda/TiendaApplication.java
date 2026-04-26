package com.retail.tienda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.NetworkInterface;

@SpringBootApplication
public class TiendaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TiendaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inicio!!!");

        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println("Tu identificador único (MAC): " + sb.toString());
            if (sb.toString().equals("08-8F-C3-DB-D0-F5")) {
                System.out.println("Es igual");
            } else {
                System.out.println("Los identificadores no coinciden");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
