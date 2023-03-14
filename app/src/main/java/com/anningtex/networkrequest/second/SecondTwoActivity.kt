package com.anningtex.networkrequest.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anningtex.networkrequest.R
import com.anningtex.networkrequest.second.event.UserEvent
import com.anningtex.networkrequest.utils.RxBus
import com.anningtex.networkrequest.utils.registerInBus
import kotlinx.android.synthetic.main.activity_second_two.*

/**
 * @author Song
 */
class SecondTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_two)

        RxBus.observe<UserEvent>()
            .subscribe { t: UserEvent? ->
                kotlin.run {
                    tv_text.text = "name:${t?.user?.name} age:${t?.user?.age}"
                }
            }
            .registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }
}