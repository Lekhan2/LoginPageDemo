package personal.project.log_in

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import personal.project.log_in.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignInBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignInBinding.inflate(layoutInflater)
        auth=FirebaseAuth.getInstance()

        setContentView(binding.root)

        binding.tvNotUser.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))

        }
        binding.btnSignIn.setOnClickListener{
            val email=binding.etEmail.text.toString()
            val password=binding.etPass.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Welcome ${email}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else {
                Toast.makeText(this, "Enter all the field", Toast.LENGTH_SHORT).show()
            }
        }


    }
}