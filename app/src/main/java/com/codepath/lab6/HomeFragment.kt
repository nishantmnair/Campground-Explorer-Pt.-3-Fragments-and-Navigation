package com.codepath.lab6

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "HomeFragment"
private const val API_KEY = BuildConfig.API_KEY
private const val ALERTS_URL = "https://developer.nps.gov/api/v1/alerts?api_key=${API_KEY}"

class HomeFragment : Fragment() {
    private val alerts = mutableListOf<Alert>()
    private lateinit var alertsRecyclerView: RecyclerView
    private lateinit var alertsAdapter: AlertAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        
        alertsRecyclerView = view.findViewById(R.id.alertsRecyclerView)
        alertsRecyclerView.layoutManager = LinearLayoutManager(context)
        alertsAdapter = AlertAdapter(view.context, alerts)
        alertsRecyclerView.adapter = alertsAdapter

        fetchAlerts()
        
        return view
    }

    private fun fetchAlerts() {
        val client = AsyncHttpClient()
        client.get(ALERTS_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch alerts: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched alerts: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        AlertResponse.serializer(),
                        json.jsonObject.toString()
                    )
                    parsedJson.data?.let { list ->
                        alerts.addAll(list)
                        alertsAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }
        })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
