// メールアカウントを新規作成・ログイン
package com.example.todoapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    var emailFormEditText: EditText? = null
    var passwordFormEditText: EditText? = null
    var data: Intent? = null
    var mAuth: FirebaseAuth? = null

    companion object {
        private const val TAG = "EmailPassword"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailFormEditText = findViewById(R.id.email_log_in_edit_text)
        passwordFormEditText = findViewById(R.id.password_log_in_edit_text)
        mAuth = FirebaseAuth.getInstance()
    }

    private fun checkEmpty(): Boolean {
        if (TextUtils.isEmpty(emailFormEditText!!.text)) {
            Log.d("MainActivity", "何も記入されていません")
            return false
        }
        if (TextUtils.isEmpty(passwordFormEditText!!.text)) {
            Log.d("MainActivity", "何も記入されていません")
            return false
        }
        return true
    }

    fun loginMailButton(v: View?) {
        signIn(emailFormEditText!!.text.toString(), passwordFormEditText!!.text.toString())
        setResult(RESULT_OK, data)
    }

    fun addMailButton(v: View?) {
        createAccount(emailFormEditText!!.text.toString(), passwordFormEditText!!.text.toString())
        setResult(RESULT_OK, data)
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!checkEmpty()) {
            return
        }
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { // ログインに成功したら、ログインしたユーザーの情報でUIを更新
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(this@MainActivity, "新規作成に成功しました！", Toast.LENGTH_SHORT).show()
                    changeActivity()
                } else { // サインインに失敗した場合は、ユーザーにメッセージを表示
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this@MainActivity, "Authentication failed.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!checkEmpty()) {
            return
        }
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // ログインに成功したら、ログインしたユーザーの情報でUIを更新
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this@MainActivity, "ログインに成功しました！", Toast.LENGTH_SHORT).show()
                    changeActivity()
                } else {
                    // サインインに失敗した場合は、ユーザーにメッセージを表示
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setMessage(task.exception!!.message)
                        .setTitle("Error!")
                        .setPositiveButton(android.R.string.ok, null)
                    val dialog = builder.create()
                    dialog.show()
                }
            }
    }

    private fun changeActivity() {
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)
        finish()
    }
}