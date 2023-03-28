package com.example.codepath_project_4_tip_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var BaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var Percent: TextView
    private lateinit var TipAmount: TextView
    private lateinit var TotalAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BaseAmount = findViewById(R.id.BaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        Percent = findViewById(R.id.Percent)
        TipAmount = findViewById(R.id.TipAmount)
        TotalAmount = findViewById(R.id.TotalAmount)

        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, p1: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                Percent.text = "$p1%"
                calculateTipandTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        BaseAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")
                calculateTipandTotal()
            }

        })
    }

    private fun calculateTipandTotal() {
        val baseAmount = BaseAmount.text.toString().toDouble()
        val percent = seekBarTip.progress
        val tipAmount = baseAmount * percent / 100
        val totalAmount = baseAmount + tipAmount
        TipAmount.text = tipAmount.toString()
        TotalAmount.text = totalAmount.toString()
    }
}