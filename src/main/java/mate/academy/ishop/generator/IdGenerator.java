package mate.academy.ishop.generator;

public class IdGenerator implements Generator {
    private static Long idOrder = 0L;
    private static Long idBacket = 0L;
    private static Long idUser = 0L;
    private static Long idItem = 0L;
    private static Long idRole = 0L;

    public static Long getOrderId() {
        return idOrder++;
    }

    public static Long getBacketId() {
        return idBacket++;
    }

    public static Long getItemId() {
        return idItem++;
    }

    public static Long getUserId() {
        return idUser++;
    }

    public static Long getRoleId() {
        return idRole++;
    }
}
