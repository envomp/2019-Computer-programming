package ee.taltech.iti0202.idcode;

public class IDCode {
    private enum Gender {
        MALE, FEMALE
    }

    public static boolean isIDCodeCorrect(String idCode) {
        if (idCode.length() == 11 && idCode.matches("[0-9]+")) {
            boolean[] correctness = {isGenderNumberCorrect(idCode), isYearNumberCorrect(idCode), isMonthNumberCorrect(idCode), isDayNumberCorrect(idCode), isQueueNumberCorrect(idCode), isControlNumberCorrect(idCode)};
            for (boolean b : correctness) if (!b) return false;
            return true;
        }
        return false;
    }

    private static boolean isGenderNumberCorrect(String idCode) {
        return idCode.substring(0, 1).matches("^[1-6]$");
    }

    private static boolean isYearNumberCorrect(String idCode) {
        return true;
    }

    private static boolean isMonthNumberCorrect(String idCode) {
        int month = Integer.parseInt(idCode.substring(3, 5));
        return month > 0 && month < 13;
    }

    private static boolean isDayNumberCorrect(String idCode) {
        int year = getFullYear(idCode);
        boolean leap = isLeapYear(year);
        String month = idCode.substring(3, 5);
        int day = Integer.parseInt(idCode.substring(5, 7));
        if (Integer.parseInt(month) == 2) {
            if (leap) {
                return day > 0 && day < 30;
            } else {
                return day > 0 && day < 29;
            }
        } else if (month.matches("^[4|6|9|11]$")) {
            return day > 0 && day < 31;
        } else {
            return day > 0 && day < 32;
        }

    }

    private static boolean isQueueNumberCorrect(String idCode) {
        return true;
    }

    private static boolean isControlNumberCorrect(String idCode) {
        int[] multipliers_1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        int[] multipliers_2 = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
        int control_number = 0;
        for (int i = 0; i < 10; i++) {
            control_number += multipliers_1[i] * Character.getNumericValue(idCode.charAt(i));
        }
        control_number %= 11;
        if (control_number == 10) {
            control_number = 0;
            for (int i = 0; i < 10; i++) {
                control_number += multipliers_2[i] * idCode.charAt(i);
            }
            control_number %= 11;
        }
        if (control_number == 10) {
            control_number = 0;
        }
        return control_number == Integer.parseInt(idCode.substring(10));
    }

    private static boolean isLeapYear(int fullYear) {
        if (fullYear % 400 == 0) {
            return true;
        } else if (fullYear % 100 == 0) {
            return false;
        } return fullYear % 4 == 0;
    }

    public static String getInformationFromIDCode(String idCode) {
        if (!isIDCodeCorrect(idCode)) {
            return "Given invalid ID code!";
        }
        return "This is a " + getGender(idCode) + " born on " + idCode.substring(5, 7) + "." + idCode.substring(3, 5) + "." + getFullYear(idCode);
    }

    public static Gender getGender(String idCode) {
        String genderNumber = idCode.substring(0, 1);
        if (genderNumber.matches("^[1|3|5]$")) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

    public static int getFullYear(String idCode) {
        return Integer.parseInt(idCode.substring(1, 3)) + 1700 + ((Integer.parseInt(idCode.substring(0, 1)) + 1) / 2) * 100;
    }

    public static void main(String[] args) {
        System.out.println(isDayNumberCorrect("39906310831"));
    }
}
