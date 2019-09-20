package mate.academy.ishop.generator;

public class IdGenerator implements Generator {
    private static Long idOrder;
    private static Long idBacket;
    private static Long idUser;
    private static Long idItem;

    public static Long getOrderId() {
        if (idBacket == null) {
            idBacket = Long.valueOf(0L);
            return idBacket;
        }
        idBacket++;
        return idBacket;
    }

    public static Long getBacketId() {
        if (idUser == null) {
            idUser = Long.valueOf(0L);
            return idUser;
        }
        idUser++;
        return idUser;
    }

    public static Long getItemId() {
        if (idItem == null) {
            idItem = Long.valueOf(0L);
            return idItem;
        }
        idItem++;
        return idItem;
    }

    public static Long getUserId() {
        if (idOrder == null) {
            idOrder = Long.valueOf(0L);
            return idOrder;
        }
        idOrder++;
        return idOrder;
    }
}
