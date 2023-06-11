package hska.gassishare.ui.login;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import hska.gassishare.data.entity.User;
import hska.gassishare.data.repositories.UserRepository;

public class LoginViewModel extends AndroidViewModel {

    private UserRepository mUserRepository;

    private LiveData<List<User>> alleUser;

    public LiveData<List<User>> getAlleUser() {
        /* checke nicht warum das nicht geht
        if (alleUser == null) {
            alleUser = new LiveData<List<User>>();
        }*/

        return alleUser;
    }

    private LiveData<User> aktuellerUser;

    public LiveData<User> getAktuellerUser() {
        return aktuellerUser;
    }

    /*
    public User getAktuellerUser() {

        final User[] zueckgegebenerUser = {null};
        final Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User aktuellerUserObserved) {

                if (aktuellerUserObserved == null)
                    return;

                zueckgegebenerUser[0] = aktuellerUserObserved;

            }
        };

        return zueckgegebenerUser[1];

    }
    */


    public void setAktuellerUser(LiveData<User> aktuellerUser) {
        this.aktuellerUser = aktuellerUser;
    }

    private final MutableLiveData<String> mText;

    //private final LiveData<List<User>> mAllUsers;

    public LoginViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();

        mUserRepository = new UserRepository(application);

        alleUser = mUserRepository.getAllUsers();

    }

    public LiveData<String> getText() {
        return mText;
    }

    public boolean userExists(String username, String password) {return mUserRepository.userExists(username,password);}

    public LiveData<User> aktuellenUserAusDbHolen(String username) {return mUserRepository.getCurrentUser(username);}

}