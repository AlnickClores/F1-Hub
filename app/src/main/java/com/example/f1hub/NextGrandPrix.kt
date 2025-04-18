package com.example.f1hub

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class NextGrandPrix : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (widgetId in appWidgetIds) {

            val views = RemoteViews(context.packageName, R.layout.next_grand_prix)

            // Example: Update image and text dynamically
            views.setImageViewResource(R.id.track_map, R.drawable.jeddah_street_circuit)

            views.setTextViewText(R.id.round_number, "Round 6")
            views.setTextViewText(R.id.country_name, "🇸🇦 Saudi Arabia")
            views.setTextViewText(R.id.gp_name, "Saudi Arabian Grand Prix")
            views.setTextViewText(R.id.event_date, "18-21 April")

            views.setTextViewText(R.id.session_practice1, "Practice 1: 21:30 - 22:30\nApril 18")
            views.setTextViewText(R.id.session_practice2, "Practice 2: 01:00 - 02:00\nApril 19")
            views.setTextViewText(R.id.session_practice3, "Practice 3: 21:30 - 22:30\nApril 19")
            views.setTextViewText(R.id.session_qualifying, "Qualifying: 01:00 - 02:00\nApril 20")
            views.setTextViewText(R.id.session_race, "Race: 01:00\nApril 21")

            // Push update to widget
            appWidgetManager.updateAppWidget(widgetId, views)
        }
    }
}
