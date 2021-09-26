package collection.card.identy.job.helper;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Helper {
    public static String GenerateMd5Hash(String inputString) {
        String md5Hash = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(inputString));
            md5Hash = String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (NoSuchAlgorithmException ex) {
        }
        return md5Hash;
    }
}
