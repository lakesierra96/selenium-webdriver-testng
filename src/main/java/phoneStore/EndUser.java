package phoneStore;

public class EndUser {
    public static void main(String[] args) {
        PhoneStore phone;
        phone = getPhone("Iphone");
        phone.setPhoneName("IP17");
        System.out.println(phone.getPhoneName());

        phone = getPhone("SamSung");
        phone.setPhoneName("S24Ultra");
        System.out.println(phone.getPhoneName());
    }

    public static PhoneStore getPhone(String phoneType) {
        PhoneStore phoneStore;
        if (phoneType.equals("IPhone")) {
            phoneStore = new IPhone();
        } else if (phoneType.equals("SamSung")) {
            phoneStore = new SamSung();
        } else {
            phoneStore = new Xiaomi();
        }
        return phoneStore;
    }
}
