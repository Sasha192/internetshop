package mate.academy.ishop.generator;

public class IdGenerator implements Generator {
    private static Long idOrder;
    private static Long idBacket;
    private static Long idUser;
    private static Long idItem;
    private static Long idRole;

    static {
        idOrder = Long.valueOf(0);
        idBacket = Long.valueOf(0);
        idUser = Long.valueOf(0);
        idItem = Long.valueOf(0);
        idRole = Long.valueOf(0);
    }

    public static Long getOrderId() {
        idOrder++;
        return idOrder;
    }

    public static Long getBacketId() {
        idBacket++;
        return idBacket;
    }

    public static Long getItemId() {
        idItem++;
        return idItem;
    }

    public static Long getUserId() {
        idUser++;
        return idUser;
    }

    public static Long getRoleId() {
        idRole++;
        return idRole;
    }
}
