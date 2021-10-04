package pk.motivation.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*
import pk.motivation.R
import pk.motivation.infra.MotivationConstants
import pk.motivation.infra.SecurityPreference
import java.util.*

class SplashActivity : AppCompatActivity() {

    private lateinit var mShared: SecurityPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mShared = SecurityPreference(this)

        if(supportActionBar != null){
            supportActionBar?.hide()
        }

        verifyName()

        btnSave.setOnClickListener {
            if(validateName()){
                callMain()
            }
        }
    }

    private fun verifyName() {
        val name = mShared.getString(MotivationConstants.key.PERSON_NAME)
        if(!name.isNullOrEmpty())
            callMain()
    }

    private fun callMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun validateName(): Boolean {
        val name = editName.text.toString()
        return if (name.isEmpty()){
            Toast.makeText(this, getString(R.string.digite_o_nome), Toast.LENGTH_SHORT).show()
            false
        }else{
            mShared.storeString(MotivationConstants.key.PERSON_NAME,
                name.replaceFirstChar {
                    if (it.isLowerCase())
                        it.titlecase(Locale.getDefault())
                    else
                        it.toString()
                })
            true
        }
    }
}