package pk.motivation.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import pk.motivation.R
import pk.motivation.infra.MotivationConstants
import pk.motivation.infra.SecurityPreference
import pk.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mShared: SecurityPreference
    private var mFilter = MotivationConstants.PHRASEFILTER.All_PHRASE

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        mShared = SecurityPreference(this)

        textName.text = getString(R.string.ola)+", ${mShared.getString(MotivationConstants.key.PERSON_NAME)}!"

        handleNewPhrase(mFilter)

        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
        btnNewPhrase.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id

        val listId = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if (id == R.id.btnNewPhrase) {
            handleNewPhrase(mFilter)
        } else if (id in listId) {
            handleFilter(id)
        }

    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mFilter = MotivationConstants.PHRASEFILTER.All_PHRASE
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mFilter = MotivationConstants.PHRASEFILTER.HAPPY_PHRASE
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mFilter = MotivationConstants.PHRASEFILTER.MORNING_PHRASE
            }
        }
    }

    private fun handleNewPhrase(filter: Int) {
        textPhrase.text = Mock.getPhrase(filter)
    }
}