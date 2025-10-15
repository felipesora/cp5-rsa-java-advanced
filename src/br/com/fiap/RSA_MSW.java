/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.fiap;

/**
 *
 * @author MSWagner
 */

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

class RSA_MSW {

    public static void main(String args[]) {
        // Inicializando as variáveis
        String msg = "Essa e nossa mensagem utilizada como teste para o cp 5";
        String msgcifrada = null;
        String msgdecifrada = null;

        // Definindo valores
        BigInteger p = new BigInteger("271");
        BigInteger q = new BigInteger("397");
        BigInteger n = p.multiply(q);
        BigInteger e = new BigInteger("65537");
        // Computando a função totiente: phi(n) = (p - 1) * (q - 1)
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        // Verificando para que "d" seja o inverso multiplicativo de "e"
        BigInteger d = e.modInverse(m);

        System.out.println("p:" + p);
        System.out.println("q:" + q);
        System.out.println("n:" + n);
        System.out.println("e:" + e);
        System.out.println("d:" + d);

        // Mensagem cifrada - RSA_encrypt()
        byte[] msgBytes = msg.getBytes(StandardCharsets.US_ASCII);

        StringBuilder cifradaStringBuilder = new StringBuilder();
        for (byte b : msgBytes) {
            BigInteger msgBigInt = new BigInteger(new byte[] { b });
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
            decifradaStringBuilder.append((char) decifrada.byteValueExact());
        }
        msgdecifrada = decifradaStringBuilder.toString();

        System.out.println("msg decifrada: " + msgdecifrada);
    }
}
