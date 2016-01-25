package derpibooru.derpy.data.server;

public class DerpibooruSignInForm {
    private String mEmail;
    private String mPassword;
    private boolean mRememberUser;

    public DerpibooruSignInForm(String email, String password,
                                boolean rememberUser) {
        mEmail = email;
        mPassword = password;
        mRememberUser = rememberUser;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public boolean isRememberUser() {
        return mRememberUser;
    }
}
