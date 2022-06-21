// Googleアカウントでログイン

package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener,
    View.OnClickListener {
    private var mAuth: FirebaseAuth? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val googleSignInButton = findViewById<SignInButton>(R.id.googleLoginButton)
        googleSignInButton.setSize(SignInButton.SIZE_STANDARD)
        googleSignInButton.setOnClickListener(this)
        val loginButton = findViewById<Button>(R.id.mailLoginButton)
        loginButton.setOnClickListener(this)
    }

    private fun changeActivity() {
        val intent = Intent(this@LoginActivity, ToDoActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            if (result!!.isSuccess) {
                val account = result.signInAccount
                firebaseAuthWithGoogle(account)
            } else {
                println(result.status)
                Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(acct!!.idToken, null)
        mAuth!!.signInWithCredential(credential).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    changeActivity()
                    Log.d("LoginActivity", "ログインに成功")
                } else {
                    Toast.makeText(this@LoginActivity, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.googleLoginButton -> loginGoogle()
            R.id.mailLoginButton -> loginEmail()
        }
    }

    fun loginGoogle() {
        mAuth = FirebaseAuth.getInstance()
        // Googleのログイン認証
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient!!)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun loginEmail() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}