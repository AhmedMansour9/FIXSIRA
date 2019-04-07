package com.example.ic.fixera.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ic.fixera.Activites.Navigation;
import com.example.ic.fixera.NetworikConntection;
import com.example.ic.fixera.Presenter.LoginPresenter;
import com.example.ic.fixera.Presenter.RegisterFace_Presenter;
import com.example.ic.fixera.Presenter.RegisterGoogle_Presenter;
import com.example.ic.fixera.Model.UserRegister;
import com.example.ic.fixera.Presenter.UpdatePlayer_Presenter;
import com.example.ic.fixera.SharedPrefManager;
import com.example.ic.fixera.View.LoginView;
import com.example.ic.fixera.View.RegisterFaceView;
import com.example.ic.fixera.View.RegistergoogleView;
import com.example.ic.fixera.View.UpdatePlayerid_View;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fixsira.R;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements LoginView,RegisterFaceView,RegistergoogleView , UpdatePlayerid_View {


    public Login() {
        // Required empty public constructor
    }
    EditText E_Email,E_Password;
   View view;
    Button Sign_IN;
    TextView Sign_UP;
    NetworikConntection Network;
    LoginPresenter logiin;
    ImageView loginfac,google;
    CallbackManager mCallbackManager;
    private ProgressBar progressBar;
    public FirebaseAuth mAuth;
    public GoogleApiClient googleApiClient;
    public static final int RequestSignInCode = 7;
    GoogleSignInOptions googleSignInOptions;
    RegisterFace_Presenter regist;
    UserRegister userface;
    LoginResult loginResu;
    RegisterGoogle_Presenter Registergoogl;
    SharedPreferences.Editor Shared;
    TextView Register;
    SharedPreferences.Editor shareRole;
    RelativeLayout Relativelogin;
    String useer;
    String email;
    String useergoogle;
    String user;
    SharedPreferences shared;
    TextView guest;
    UpdatePlayer_Presenter updatePlayer_presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_login, container, false);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        logiin=new LoginPresenter(getActivity(),this);
        updatePlayer_presenter=new UpdatePlayer_Presenter(getActivity(),this);
        guest=view.findViewById(R.id.guest);
        loginfac=view.findViewById(R.id.loginfac);
//        google=view.findViewById(R.id.google);
        regist=new RegisterFace_Presenter(getActivity(),this);
        Registergoogl=new RegisterGoogle_Presenter(getActivity(),this);
        mCallbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        logiin=new LoginPresenter(getActivity(),this);

//        GoogleSignOpition();
//        LoginGoogle();
        LoginFac();
        progressBar=view.findViewById(R.id.progressBarlogin);
        Network=new NetworikConntection(getContext());
        Sign_IN=view.findViewById(R.id.Sign_IN);
        Sign_UP=view.findViewById(R.id.textsignup);
        E_Email=view.findViewById(R.id.E_Email);
        E_Password=view.findViewById(R.id.E_Password);
        GotToTabsLayout();
        GoToRegister();
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getActivity()).saveRole("role");
                startActivity(new Intent(getContext(),Navigation.class));
            }
        });

         return view;
    }
    public void setLanguage(){
        shared=getActivity().getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
        if(Lan!=null) {
            Locale locale = new Locale(Lan);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getActivity().getBaseContext().getResources().updateConfiguration(config,
                    getActivity().getBaseContext().getResources().getDisplayMetrics());
        }
    }
    public void GotToTabsLayout(){
        Sign_IN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Network.isNetworkAvailable(getContext())){
                    FUtilsValidation.isEmpty(E_Email,getResources().getString(R.string.insertemail));
                    FUtilsValidation.isEmpty(E_Password, getResources().getString(R.string.insertpassword));

                    if (!E_Email.getText().toString().equals("") && !E_Password.getText().toString().equals("")) {
                        UserRegister user = new UserRegister();
                        user.setEmail(E_Email.getText().toString());
                        user.setPassword(E_Password.getText().toString());
                        progressBar.setVisibility(View.VISIBLE);
                        email=E_Email.getText().toString();
                        logiin.Login(user);
                    }
                }else {
                    Toast.makeText(getContext(),getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                }
                }

//                getFragmentManager().beginTransaction().replace(R.id.flContent,new TabsLayouts()).commit();

        });
    }
    public void GoToRegister(){
        Sign_UP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.flContent,new Register()).commit();
            }
        });
    }
    private Boolean ValidateEmail(){
        String EMAIL=E_Email.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            E_Email.setError(getResources().getString(R.string.invalidemail));

            return false;
        }else if(!E_Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z-Z0-9._-]+\\.+[a-z]+")){
            E_Email.setError(getResources().getString(R.string.invalidemail));
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void LoginGoogle() {

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Network.isNetworkAvailable(getActivity())) {
                    Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(AuthIntent, RequestSignInCode);
                }else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestSignInCode) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()) {
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                FirebaseUserAuth(googleSignInAccount);
            }
        }
    }
//    public void GoogleSignOpition(){
//        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        googleApiClient = new GoogleApiClient.Builder(getActivity())
//                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//                    }
//                } /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
//                .build();
//    }
    @Override
    public void onPause() {
        super.onPause();
        if(googleApiClient!=null) {
            googleApiClient.stopAutoManage(getActivity());
            googleApiClient.disconnect();

        }
    }
    public void FirebaseUserAuth(final GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        mAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task AuthResultTask) {
                        if (AuthResultTask.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            useergoogle=user.getDisplayName();
                            final String emaail=user.getEmail();
                            UserRegister use=new UserRegister();
                            String y =googleSignInAccount.getId();

                            use.setFirstName(useergoogle);
                            use.setEmail(emaail);
                            use.setId(y);

                            progressBar.setVisibility(View.VISIBLE);
                            Registergoogl.Registergoogle(use);




                        } else {
//                            Toast.makeText(LoginPresenter.this, "Turn on Internet", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void LoginFac(){
        loginfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("email", "public_profile"));

                    LoginManager.getInstance().registerCallback(mCallbackManager,
                            new FacebookCallback<LoginResult>() {
                                @Override
                                public void onSuccess(LoginResult loginResult) {
                                    Log.d("Success", "LoginPresenter");
                                    loginResu = loginResult;
                                    handleFacebookAccessToken(loginResult.getAccessToken());
                                }

                                @Override
                                public void onCancel() {
                                Toast.makeText(getContext(), "LoginPresenter Cancel", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onError(FacebookException exception) {
                                    Toast.makeText(getContext(), exception.toString(), Toast.LENGTH_LONG).show();
                                }
                            });

            }
        });
    }
    private void handleFacebookAccessToken(AccessToken token) {

        progressBar.setVisibility(View.VISIBLE);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            GraphRequest data_request = GraphRequest.newMeRequest(
                                    loginResu.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONObject object,
                                                GraphResponse response) {
                                            try {
                                                String facebook_id = object.getString("id");
                                                progressBar.setVisibility(View.GONE);
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                useer=object.getString("name");
                                                final String emaail=object.getString("email");
                                                final String id=user.getUid();
                                                userface = new UserRegister();
                                                userface.setFirstName(useer);
                                                userface.setEmail(emaail);
                                                userface.setId(facebook_id);
                                                progressBar.setVisibility(View.VISIBLE);
                                                regist.RegisterFace(userface);
                                            } catch (JSONException e) {
                                                // TODO Auto-generated catch block
                                            }
                                        }
                                    });
                            Bundle permission_param = new Bundle();
                            permission_param.putString("fields", "id,name,email");
                            data_request.setParameters(permission_param);
                            data_request.executeAsync();

                        }
                    }
                });
    }
    @Override
    public void openMain(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        SharedPrefManager.getInstance(getActivity()).saveRole(null);
        progressBar.setVisibility(View.GONE);
        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        String playerid= status.getSubscriptionStatus().getUserId();

        updatePlayer_presenter.SendPlayerId(a,playerid);

        startActivity(new Intent(getContext(),  Navigation.class));
        getActivity().finish();
    }

    @Override
    public void OpenRole(String usertoken) {
        Shared.putString("uservendor",usertoken);
        Shared.apply();
        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
//        status.getPermissionStatus().getEnabled();
//        status.getSubscriptionStatus().getSubscribed();
//        status.getSubscriptionStatus().getUserSubscriptionSetting();
        String playerid= status.getSubscriptionStatus().getUserId();

        updatePlayer_presenter.SendPlayerId(usertoken,playerid);

        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(getContext(),Navigation.class));
        getActivity().finish();
        SharedPrefManager.getInstance(getActivity()).saveRole(null);
    }

    @Override
    public void showError(String error) {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void Invalidemail(String password) {
        Toast.makeText(getActivity(), ""+getResources().getString(R.string.invalidemail), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainFace(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(getContext(),Navigation.class));
        getActivity().finish();
        SharedPrefManager.getInstance(getActivity()).saveRole(null);
        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
//        status.getPermissionStatus().getEnabled();
//        status.getSubscriptionStatus().getSubscribed();
//        status.getSubscriptionStatus().getUserSubscriptionSetting();
        String playerid= status.getSubscriptionStatus().getUserId();

        updatePlayer_presenter.SendPlayerId(a,playerid);

    }

    @Override
    public void showErrorFace(String error) {
        Toast.makeText(getActivity(), ""+getResources().getString(R.string.invalidemail), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainGoogle(String y) {
        Shared.putString("logggin",y);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(getContext(), Navigation.class));
        getActivity().finish();
        SharedPrefManager.getInstance(getActivity()).saveRole(null);

    }

    @Override
    public void showErrorGoogle(String error) {
        Toast.makeText(getActivity(), ""+getResources().getString(R.string.invalidemail), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setLanguage();
    }

    @Override
    public void playerid() {

    }

    @Override
    public void Error() {
        progressBar.setVisibility(View.GONE);

    }


    @Override
    public void onStart() {
        super.onStart();
    }
}

