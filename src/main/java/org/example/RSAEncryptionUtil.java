package org.example;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAEncryptionUtil {

    // Encrypt data using a public key
    public static byte[] encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    // Decrypt data using a private key
    public static String decrypt(byte[] encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedData);
        return new String(decryptedBytes);
    }

    // Convert a Base64-encoded public key string to PublicKey
    public static PublicKey getPublicKeyFromString(String publicKeyBase64) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // Convert a Base64-encoded private key string to PrivateKey
    public static PrivateKey getPrivateKeyFromString(String privateKeyBase64) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static void main(String[] args) {
        try {
            // Base64-encoded public and private keys obtained from RSAKeyGenerator
            String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxE9g0HDbHZ3vBjd4u8BX8zltEhfoiMnB3U5P6w1Fc5I7U1hevt8I+pkXxIre1tpmkG7ETpDavbeHHBg87BQwuMSU3Zn5Btv5wpgv6OfNDX8YwTfou6lPlnl9GQ+FMKIklCz3anPlhWUR3y6fMyM9mN6wD3nsImD/CZeAevsig+9cvz6D45TLdyvupVueNt2/ogiWiElqcliF1HpF57m5qr5jl3X0vo2dBNayHdTKH//XIH4qtJ6qhgaBi2eytWnsOUPUdn7CqawgPaEiDUsiMNrLF/PZav77q0FK7r/Ck9RykAatOybKO0MdevNLL59DbzBLA09ildVtwonzn5zsOQIDAQAB";
            String privateKeyBase64 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDET2DQcNsdne8GN3i7wFfzOW0SF+iIycHdTk/rDUVzkjtTWF6+3wj6mRfEit7W2maQbsROkNq9t4ccGDzsFDC4xJTdmfkG2/nCmC/o580NfxjBN+i7qU+WeX0ZD4UwoiSULPdqc+WFZRHfLp8zIz2Y3rAPeewiYP8Jl4B6+yKD71y/PoPjlMt3K+6lW5423b+iCJaISWpyWIXUekXnubmqvmOXdfS+jZ0E1rId1Mof/9cgfiq0nqqGBoGLZ7K1aew5Q9R2fsKprCA9oSINSyIw2ssX89lq/vurQUruv8KT1HKQBq07Jso7Qx1680svn0NvMEsDT2KV1W3CifOfnOw5AgMBAAECggEAJ2GiMyaUumwKvaVGremP6z33tMPBlSFeaBJUA3wp1N2+04E8no9ZOoaYR0gxnIn7PdnOvVEfZKRK8TamkBibzpE3KP6Av+DEycYkhFVuJBk9YtOft3fnfLEaCLs9laxNaMFdkAg4gOpt66crbkORUZfI243yslHhj+/J6UH0omE6OKwH5dRI5BVf4YhtMsMVJqSuFN7hLonAft6NA6eWVgFb3Pzxt67H7q0N+hM92UCyGJ3Kx1dv4qcvw3NeH51euccXq4TA3z1qdJPeeXiqcoecGCOW2i/iJEKq8l1GXgzYIEWQVnmyndsDhAoDyVWmjYH0ZxgqZ/DX3Ad4uTjKhQKBgQDfbN+H1Lp7CRQf7w0ILLsbwMu6ZgKcZhdjzlIQoZt78fFJUCGwaywKw1yx6OGr9Shk74QAILtxWIZ1dCyEujZmHlcNJt9n7Xj4dJzi4pE7oo2SYTAzb3+fCs4RFITuJFHdFe8eOPEBaWg0jMVhw0euVcND4ZewfhS3wxH3eNj5fQKBgQDg7nThFSc5KzKOtxRxnS91iZvASI8NxVHzcztnlvt6teGpmntvaMuUAlH4AbcaPnBlDnD9Z83gvXpQfgoav5Vwz1gPJ56HbYYCP6lRSJpUKJb/Yl1/7eHVdLcjsWcoWj4pXhQFuiY5h+xW8JzbAx40yzeq0e5D56mVFpl8brQabQKBgD2xIkevRY6TgysOJtsK/t867QYbBL2Kswqr/Zz6AqTGuYk0xxiffxm5t+6oDygcGXtFZ31hjGBPwVDNMz2y7TJlTkQjZ6yatbxk9qzvQork55Jzo4uQTfzsTtoIgGwBO1mApPYDYwiwwkVAWYAVG8XXiINF10y0g6P6CQv8Im1BAoGAayJIuzhnETy4/O7IpwC2LSaY77HfqTB1wx38im+IYj8lN7Ij3sGsTVf5kMXIX5Cj2g5R1PDPR58YFiSiTXE5DZ1ElRtKz7hj3shRriyFjVYz7Xn9jMRSD2hjHnYN61NoTi3IHZ43PKm38QTzwQEwLvcoEY2iDefWhvNXscPMttUCgYEAmh0aQ0qK0yGvBFPXlPeHOLLx4boxx35t80pZRMZ7X+Xi848h+gV69aOiidkiPE7tXe308CHislIURcqNXJege9nCauhBZv4W3umYrAwexnEt8MJyMMp9OBOryOX6td5PZqCE2QG5glH6aUYrKSDgCPtcynSEC8aKl5K7hTui73c=";

            // Convert Base64 strings to PublicKey and PrivateKey
            PublicKey publicKey = getPublicKeyFromString(publicKeyBase64);
            PrivateKey privateKey = getPrivateKeyFromString(privateKeyBase64);

            // Original data
            String originalData = "Hello, RSA Encryption and Decryption!";

            // Encrypt the data using the public key
            byte[] encryptedData = encrypt(originalData, publicKey);
            System.out.println("Encrypted Data: " + Base64.getEncoder().encodeToString(encryptedData));

            // Decrypt the data using the private key
            String decryptedData = decrypt(encryptedData, privateKey);
            System.out.println("Decrypted Data: " + decryptedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
