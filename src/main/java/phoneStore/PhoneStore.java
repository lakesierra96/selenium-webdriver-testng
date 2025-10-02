package phoneStore;

public abstract class PhoneStore {
    protected String phoneName;

    protected abstract void setPhoneName(String phoneName);

    protected abstract String getPhoneName();
}
