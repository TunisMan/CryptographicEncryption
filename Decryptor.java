import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class Decryptor
{
    // AES algoritmasının adını bir değişkene atıyoruz.
    private static final String ALGORITHM = "AES";



    // Veriyi çözmek için bir fonksiyon.

    public static String decrypt(String encryptedData, SecretKey key) throws Exception
        {

        // Cipher sınıfını kullanarak AES algoritmasını başlat.
        Cipher cipher = Cipher.getInstance(ALGORITHM);


        // Cipher'ı şifre çözme moduna geçir.
        cipher.init(Cipher.DECRYPT_MODE, key);


        // Şifrelenmiş String veriyi Base64 formatından byte dizisine dönüştür.
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);


        // Şifre çözme işlemini gerçekleştir.
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);


        // Şifrelenmiş metni çözerek orijinal metni elde eder.
        return new String(decryptedBytes, StandardCharsets.UTF_8);  // Decrypted byte'ları UTF-8 formatında string'e çeviriyoruz

     }

    // Verilen anahtarı (16 karakter) SecretKey türüne çeviren bir fonksiyon.
    public static SecretKey getKeyFromString(String keyString)
        {

        // String anahtarını Byte dizisine çevirir.
        byte[] keyBytes = keyString.getBytes();

        // Byte dizisini AES algoritması için SecretKey nesnesine dönüştürür.
        return new SecretKeySpec(keyBytes, ALGORITHM);

        }

    public static void main(String[] args) throws Exception {

        try
        {
            // Şifrelenmiş metin için değişken
            String encryptedText = "8LAYmucTMSUqqDr5moKQRmjOyXyYZENZPEoUavoyeQ8FAYegzeWphyy6sJGrc+VT";

            // Şifreleme işlemi sırasında kullandığımız aynı 16 karakter uzunluğundaki anahtarı gerekiyor.
            String manualKey = "1234567890123456";

            // String olarak girdiğimiz anahtarı SecretKey nesnesine çevirir.
            SecretKey secretKey = getKeyFromString(manualKey);

            // Şifrelenmiş metni AES algoritması ile çözer
            String decryptedText = decrypt(encryptedText, secretKey);

            // Çözülen metni ekrana yazdırır
            System.out.println("Çözülen metin: " + decryptedText);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}