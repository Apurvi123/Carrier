package com.example.carrier.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carrier.R
import com.example.carrier.repository.ShiftRepository
import com.example.carrier.repository.remotesource.model.MessageBody
import com.example.carrier.repository.remotesource.model.ShiftDetails
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShiftDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var shiftRepository: ShiftRepository
    private var shiftDetails: ShiftDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        AndroidInjection.inject(this)

        bindViews()
    }

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            // sending in a hardcoded shift Id value npt used by API as it's mocked but to fetch from local
            shiftDetails = shiftRepository.getShiftDetails(SHIFT_ID)

            shiftDetails?.let {
                statusView.text = it.status
                driverIdView.text = it.driverId.toString()
                driverNameView.text = it.driverName
            }
        }
    }

    private fun initView() {
        setContentView(R.layout.activity_shift_details)
    }

    private lateinit var statusView: TextView
    private lateinit var driverIdView: TextView
    private lateinit var driverNameView: TextView
    private lateinit var sendHelloButton: Button

    private fun bindViews() {
        statusView = findViewById(R.id.activity_shift_details_status)
        driverIdView = findViewById(R.id.activity_shift_details_driver_id)
        driverNameView = findViewById(R.id.activity_shift_details_driver_name)
        sendHelloButton = findViewById(R.id.activity_shift_details_button_hello)

        sendHelloButton.setOnClickListener {
            val message = shiftRepository.sendMessage(MessageBody(MESSAGE))
            Log.v(TAG, "Send Message result: ${message?.result}")
            Toast.makeText(this, "Message sent result : ${message?.result}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        shiftRepository.clearAll()
    }
}

private const val TAG = "ShiftDetailsActivity"
private const val SHIFT_ID = 1
private const val MESSAGE = "Hello!"