package mate.academy.ishop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;

public class HashingUtil {
    private static Logger logger = Logger.getLogger(HashingUtil.class);

    public static String hashPassword(String password, byte[] seed) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(seed);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b: digest) {
                hashedPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return hashedPassword.toString();
    }

    public static byte[] getSeed() {
        byte[] seed = new byte[16];
        new SecureRandom().nextBytes(seed);
        return seed;
    }

}
