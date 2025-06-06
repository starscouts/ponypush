package io.heckel.ntfy.msg

import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.heckel.ntfy.db.Action
import io.heckel.ntfy.db.Attachment
import io.heckel.ntfy.db.Icon
import io.heckel.ntfy.db.Notification
import io.heckel.ntfy.util.joinTags
import io.heckel.ntfy.util.toPriority
import java.io.UnsupportedEncodingException
import java.lang.reflect.Type


class NotificationParser {
    private val gson = Gson()

    fun parse(s: String, subscriptionId: Long = 0, notificationId: Int = 0): Notification? {
        val notificationWithTopic = parseWithTopic(s, subscriptionId = subscriptionId, notificationId = notificationId)
        return notificationWithTopic?.notification
    }

    private fun decodeBase64(coded: String): String? {
        var valueDecoded = ByteArray(0)
        try {
            valueDecoded = Base64.decode(coded.toByteArray(charset("UTF-8")), Base64.DEFAULT)
        } catch (e: UnsupportedEncodingException) { }
        return valueDecoded.toString(Charsets.UTF_8)
    }

    fun parsePonypush(message: String): String {
        if (message.contains("(\$PA")) {
            val parts = message.split("(\$PA")[1].split("\$\$")

            if (parts.count() >= 3) {
                return decodeBase64(parts[1]) ?: "<invalid>"
            } else {
                return "<invalid>"
            }
        } else {
            return message
        }
    }

    fun parseWithTopic(s: String, subscriptionId: Long = 0, notificationId: Int = 0): NotificationWithTopic? {
        val message = gson.fromJson(s, Message::class.java)
        if (message.event != ApiService.EVENT_MESSAGE) {
            return null
        }
        val attachment = if (message.attachment?.url != null) {
            Attachment(
                name = message.attachment.name,
                type = message.attachment.type,
                size = message.attachment.size,
                expires = message.attachment.expires,
                url = message.attachment.url,
            )
        } else null
        val actions = if (message.actions != null) {
            message.actions.map { a ->
                Action(
                    id = a.id,
                    action = a.action,
                    label = a.label,
                    clear = a.clear,
                    url = a.url,
                    method = a.method,
                    headers = a.headers,
                    body = a.body,
                    intent = a.intent,
                    extras = a.extras,
                    progress = null,
                    error = null
                )
            }
        } else null
        val icon: Icon? = if (message.icon != null && message.icon != "") Icon(url = message.icon) else null
        val notification = Notification(
            id = message.id,
            subscriptionId = subscriptionId,
            timestamp = message.time,
            title = parsePonypush(message.title ?: ""),
            message = parsePonypush(message.message),
            encoding = message.encoding ?: "",
            priority = toPriority(message.priority),
            tags = joinTags(message.tags),
            click = message.click ?: "",
            icon = icon,
            actions = actions,
            attachment = attachment,
            notificationId = notificationId,
            deleted = false
        )
        return NotificationWithTopic(message.topic, notification)
    }

    /**
     * Parse JSON array to Action list. The indirection via MessageAction is probably
     * not necessary, but for "good form".
     */
    fun parseActions(s: String?): List<Action>? {
        val listType: Type = object : TypeToken<List<MessageAction>?>() {}.type
        val messageActions: List<MessageAction>? = gson.fromJson(s, listType)
        return messageActions?.map { a ->
            Action(
                id = a.id,
                action = a.action,
                label = a.label,
                clear = a.clear,
                url = a.url,
                method = a.method,
                headers = a.headers,
                body = a.body,
                intent = a.intent,
                extras = a.extras,
                progress = null,
                error = null
            )
        }
    }

    data class NotificationWithTopic(val topic: String, val notification: Notification)
}
