package cxhub.demo.app

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.mail.libnotify.api.NotificationFactory
import ru.mail.libnotify.api.UserProperty
import ru.mail.libnotify.api.UserPropertyApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Узнать instanceId конкретного пользователя
        Log.i("MY", NotificationFactory.get(this).instanceId)

        // Узнать пуш-токен
        NotificationFactory.get(this).getPushToken { pushToken ->
            Log.d("MY", "Push token: $pushToken")
        }

        // Пример: сохранить пользовательское свойство
        val userProperty = UserProperty("some_property_name", "some_property_value")
        NotificationFactory.get(this)
            .setUserProperty(userProperty, object : UserPropertyApi.OnResultListener {
                override fun onSuccess() {
                    Toast.makeText(this@MainActivity, "onSuccess", Toast.LENGTH_SHORT).show()
                }

                override fun onFailed(exception: Throwable?) {
                    Toast.makeText(
                        this@MainActivity,
                        "onFailed: " + exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}