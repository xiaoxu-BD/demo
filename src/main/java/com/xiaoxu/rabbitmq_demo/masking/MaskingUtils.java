package com.xiaoxu.rabbitmq_demo.masking;

public final class MaskingUtils {
    private MaskingUtils() {
    }

    public static String maskName(String name) {
        if (name == null || name.isBlank()) {
            return name;
        }
        if (name.length() <= 1) {
            return "*";
        }
        return name.charAt(0) + "*".repeat(name.length() - 1);
    }

    public static String maskIdNumber(String idNumber) {
        if (idNumber == null || idNumber.isBlank()) {
            return idNumber;
        }
        if (idNumber.length() <= 8) {
            return "*".repeat(idNumber.length());
        }
        return idNumber.substring(0, 4) + "****" + idNumber.substring(idNumber.length() - 4);
    }

    public static String maskPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            return phone;
        }
        if (phone.length() <= 7) {
            return "*".repeat(phone.length());
        }
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
    }

    public static String maskAddress(String address) {
        if (address == null || address.isBlank()) {
            return address;
        }
        if (address.length() <= 6) {
            return "*".repeat(address.length());
        }
        return address.substring(0, 6) + "****";
    }
}
