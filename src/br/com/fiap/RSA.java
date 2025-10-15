package br.com.fiap;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class RSA {

    private BigInteger n, e, d;

    // Construtor que gera as chaves RSA
    public RSA(BigInteger p, BigInteger q, BigInteger e) {
        BigInteger n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(m);

        this.n = n;
        this.e = e;
        this.d = d;
    }

    // Criptografar a mensagem - igual à lógica do RSA_MSW_File
    public String encrypt(String message) {
        byte[] msgBytes = message.getBytes(StandardCharsets.US_ASCII);
        StringBuilder cifrada = new StringBuilder();

        for (byte b : msgBytes) {
            // byte → valor positivo
            BigInteger msgBigInt = BigInteger.valueOf(b & 0xFF);
            BigInteger encrypted = msgBigInt.modPow(e, n);
            cifrada.append(encrypted).append(" ");
        }

        // Remove espaço extra no final
        return cifrada.toString().trim();
    }

    // Descriptografar a mensagem - igual à lógica do RSA_MSW_File
    public String decrypt(String encryptedMessage) {
        String[] parts = encryptedMessage.split(" ");
        StringBuilder decifrada = new StringBuilder();

        for (String part : parts) {
            BigInteger cifradaBigInt = new BigInteger(part);
            BigInteger decifradaBigInt = cifradaBigInt.modPow(d, n);
            decifrada.append((char) decifradaBigInt.intValue());
        }

        return decifrada.toString();
    }

    // Getters
    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
}
