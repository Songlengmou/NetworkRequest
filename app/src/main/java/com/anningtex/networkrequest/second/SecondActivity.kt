package com.anningtex.networkrequest.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.anningtex.networkrequest.R
import com.anningtex.networkrequest.second.event.User
import com.anningtex.networkrequest.second.event.UserEvent
import com.anningtex.networkrequest.utils.MessageEvent
import com.anningtex.networkrequest.utils.RxBus
import kotlinx.android.synthetic.main.activity_second.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.startActivity

/**
 * @author Song
 */
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        btn_launch.setOnClickListener {
            val user = User("小明", 15)
            RxBus.send(UserEvent(user))
            startActivity<SecondTwoActivity>()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        Log.e("TAG", "onMessageEvent: " + event.name)
        val name: String = event.name
        et_data.setText(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}