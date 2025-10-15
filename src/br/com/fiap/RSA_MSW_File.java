/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fiap;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RSA_MSW_File {

    public static void main(String args[]) {
        // Ler a mensagem do arquivo
        String msg = readMessageFromFile("C:\\Users\\labsfiap\\Downloads\\RSA_MSW\\RSA_MSW\\src\\main\\java\\br\\uam\\rsa_msw\\mensagem.txt");
        String msgcifrada = null;
        String msgdecifrada = null;

        // Definindo valores para o RSA
        BigInteger p = new BigInteger("271");
        BigInteger q = new BigInteger("397");
        BigInteger n = p.multiply(q);
        BigInteger e = new BigInteger("65537");
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(m);

        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("n: " + n);
        System.out.println("e: " + e);
        System.out.println("d: " + d);

        // Mensagem cifrada - RSA_encrypt()
        byte[] msgBytes = msg.getBytes(StandardCharsets.US_ASCII);

        StringBuilder cifradaStringBuilder = new StringBuilder();
        for (byte b : msgBytes) {
            // Criar BigInteger com valor do byte, sem o array
            BigInteger msgBigInt = BigInteger.valueOf(b & 0xFF);  // Use & 0xFF para tratar o byte como valor positivo
            BigInteger cifrada = msgBigInt.modPow(e, n);
            cifradaStringBuilder.append(cifrada).append(" ");
        }
        msgcifrada = cifradaStringBuilder.toString().trim();

        System.out.println("msg cifrada: " + msgcifrada);

        // Mensagem decifrada - RSA_decrypt()
        String[] cifradaParts = msgcifrada.split(" ");
        StringBuilder decifradaStringBuilder = new StringBuilder();
        for (String part : cifradaParts) {
            BigInteger cifradaBigInt = new BigInteger(part);
            BigInteger decifrada = cifradaBigInt.modPow(d, n);
            // Convertendo o BigInteger de volta para o byte correspondente
            decifradaStringBuilder.append((char) decifrada.intValue());
        }
        msgdecifrada = decifradaStringBuilder.toString();

        System.out.println("msg decifrada: " + msgdecifrada);
    }

    private static String readMessageFromFile(String filename) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            if (bytes.length == 0) {
                System.err.println("O arquivo está vazio.");
                return "";
            }
            return new String(bytes, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return "";
        }
    }
}

