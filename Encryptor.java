import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class Encryptor
{

    // AES algoritmasının adını sabit bir değişkene atar.
    public static final String ALGORITHM = "AES";


    //Veriyi şifrelemek için fonksiyon.

    public static String encrypt(String data, SecretKey key) throws Exception {

        // Cipher sınıfını kullanarak AES algoritmasını başlatır
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Veriyi şifrelemek için hazır hale getirir.
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Veriyi Bytelara çevirip şifreleme işlemini gerçekleştirir.
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        // Şifrelenmiş byteları Base64 formatına çevirerek okunabilir hale getiriyoruz ve string olarak dönüştürüyoruz.
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }



    // Verilen String anahtarı (16 karakter) Secretkey türüne çeviren bir fonksiyon.

    public static SecretKey getKeyFromString(String keyString)
    {
        //String anahtarını Byte dizisine çevirir.
        byte[] keyBytes = keyString.getBytes();

        //Byte dizisini AES algortiması için SecretKey nesnesine dönüştürür.
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }


    public static void main(String[] args)
    {


        try {


            //Şifrelemek istediğimiz metni belirler.
            String originalText = "Ben şifrelenecek bir yazıyım.";

            //Manuel olarak kullanılacak 16 karakter uzunluğunda bir anahtar oluşturur.
            String manuelKey = "1234567890123456";

            //String olarak girdiğimiz anahtarı SecretKey nesnesine çevirir.
            SecretKey secretKey = getKeyFromString(manuelKey);

            //Orjinal metni AES algoritması kullanarak şifreler.
            String encryptedText = encrypt(originalText, secretKey);

            System.out.println("Şifrelenmiş metin: " + encryptedText);


        } catch (Exception e)
        {

            //Hata oluşursa hatayı bastırıyoruz.
            e.printStackTrace();

        }

    }

}


