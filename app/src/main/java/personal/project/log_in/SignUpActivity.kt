package personal.project.log_in

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import personal.project.log_in.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
   private lateinit var binding: ActivitySignUpBinding
   private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        binding.tvAlreadyAccount.setOnClickListener{
            val intent=Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            val confirmPass=binding.etRepassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()&& confirmPass.isNotEmpty()){
                if (password == confirmPass){
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { 
                        if (it.isSuccessful){
                            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                            val intent=Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                            Log.e("error", "Failed to convert ${it.exception.toString()} to an integer.")
                        }
                    }
                }
                else{
                    Toast.makeText(this, "passwords are not matching", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Enter all the field", Toast.LENGTH_SHORT).show()
            }
        }

    }
}