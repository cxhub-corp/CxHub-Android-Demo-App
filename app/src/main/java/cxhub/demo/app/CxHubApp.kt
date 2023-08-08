package cxhub.demo.app

import android.app.Application
import ru.mail.libnotify.api.FirebasePlatformManager
import ru.mail.libnotify.api.NotificationFactory

class CxHubApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Можно задать один или несколько PlatformManager в зависимости от того,
        // какой сервис вы подключили для пушей
        NotificationFactory.setPlatformManagers(FirebasePlatformManager.getInstance()/*,
            RuStorePlatformManager.getInstance(), HuaweiPlatformManager.getInstance()*/)
        NotificationFactory.initialize(this)

        // Запускает работу CxHub SDK. Может быть вызвано позже, чтобы не перегружать запуск приложения.
        // До вызова это метода SDK не будет обрабатывать уведомления
        NotificationFactory.bootstrap(this)
    }
}