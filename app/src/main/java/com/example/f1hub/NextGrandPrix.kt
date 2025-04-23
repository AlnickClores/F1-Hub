package com.example.f1hub

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.widget.LinearLayout
import android.widget.TextView

class NextGrandPrix : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (widgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.next_grand_prix)

            // Set static details
            views.setImageViewResource(R.id.track_map, R.drawable.miamigp)
            views.setTextViewText(R.id.country_name, "us United States")
            views.setTextViewText(R.id.gp_name, "Miami Grand Prix")
            views.setTextViewText(R.id.event_date, "03-05 May")

            // Session details
            val sessions = listOf(
                Triple("Practice 1", "May 3, Sat", "02:30"),
                Triple("Spring Qualifying", "May 3, Sat", "04:30"),
                Triple("Sprint", "May 4, Sun", "02:30"),
                Triple("Qualifying", "May 4, Sun", "04:00"),
                Triple("Grand Prix", "May 5, Mon", "04:00")
            )

            // Group sessions by date
            val sessionGroups = mutableMapOf<String, MutableList<Triple<String, String, String>>>()
            for (session in sessions) {
                val date = session.second
                if (!sessionGroups.containsKey(date)) {
                    sessionGroups[date] = mutableListOf()
                }
                sessionGroups[date]?.add(session)
            }

            // Reference the container that will hold dynamic sessions
            val containerId = R.id.sessions_group_container

            // Remove all previous views in the container
            views.removeAllViews(containerId)

            // Add sessions dynamically
            for ((date, sessionList) in sessionGroups) {
                // Add each session for the current date
                for (session in sessionList) {
                    val sessionLayout = RemoteViews(context.packageName, R.layout.widget_session_item)
                    sessionLayout.setTextViewText(R.id.session_name, session.first)
                    sessionLayout.setTextViewText(R.id.session_time, session.third)
                    views.addView(containerId, sessionLayout)
                }
                // Create a new TextView for the date
                val dateTextView = RemoteViews(context.packageName, R.layout.widget_text_view)
                dateTextView.setTextViewText(R.id.widget_text, date)
                views.addView(containerId, dateTextView)
            }

            // Update the widget
            appWidgetManager.updateAppWidget(widgetId, views)
        }
    }
}
