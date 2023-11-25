package org.example;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.util.Base64;

public class RSAKeyGenerator {

    public static void main(String[] args) {
        try {
            // Generate RSA key pair
            KeyPair keyPair = generateKeyPair();

            // Get the public and private keys
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Convert keys to Base64-encoded strings and print them
            String publicKeyBase64 = keyToBase64(publicKey);
            String privateKeyBase64 = keyToBase64(privateKey);

            System.out.println("Public Key (Base64): " + publicKeyBase64);
            System.out.println("Private Key (Base64): " + privateKeyBase64);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generate RSA key pair
    public static KeyPair generateKeyPair() {
        try {
            int keySize = 2048;
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convert a key to a Base64-encoded string
    public static String keyToBase64(java.security.Key key) {
        byte[] keyBytes = key.getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}


