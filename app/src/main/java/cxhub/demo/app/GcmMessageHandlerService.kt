package cxhub.demo.app

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.mail.libnotify.api.NotificationFactory

class GcmMessageHandlerService : FirebaseMessagingService() {
    // Передаем данные из пуша, пришедшего через Firebase, в CxHub SDK
    override fun onMessageReceived(message: RemoteMessage) {
        val from: String? = message.from
        val data: Map<String, String> = message.data
        Log.v(LOG_TAG, "Message received from $from with data $data")
        if (from != null) {
            NotificationFactory.deliverPushMessageIntent(this, from, data)
        }
    }
    // Если токен Firebase поменялся, передаем его в в CxHub SDK
    override fun onNewToken(token: String) {
        Log.v(LOG_TAG, "FCM token refresh. newToken: $token")
        NotificationFactory.refreshPushToken(this)
    }

    companion object {
        private const val LOG_TAG = "GcmMessageHandlerService"
    }
}