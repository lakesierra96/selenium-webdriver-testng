package phoneStore;

public class Xiaomi extends PhoneStore{
    @Override
    protected void setPhoneName(String phoneName) {
        super.phoneName = phoneName;
    }

    @Override
    protected String getPhoneName() {
        return super.phoneName;
    }
}
