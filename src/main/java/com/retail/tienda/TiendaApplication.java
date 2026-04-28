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

    }
}
