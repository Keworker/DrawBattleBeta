package com.samsung.drawbattle.classes;

public class LocalPersonalData {
    private static String personName;
    private static String personGivenName;
    private static String personFamilyName;
    private static String personEmail;
    private static String personId;

    public static void setAccountData(String personName,
                               String personGivenName,
                               String personFamilyName,
                               String personEmail,
                               String personId) {
        LocalPersonalData.personName = personName;
        LocalPersonalData.personGivenName = personGivenName;
        LocalPersonalData.personFamilyName = personFamilyName;
        LocalPersonalData.personEmail = personEmail;
        LocalPersonalData.personId = personId;
    }

    public static String getPersonName() {
        return personName;
    }
}
