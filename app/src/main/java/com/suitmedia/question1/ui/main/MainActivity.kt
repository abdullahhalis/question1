package com.suitmedia.question1.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.suitmedia.question1.R
import com.suitmedia.question1.databinding.ActivityMainBinding
import com.suitmedia.question1.ui.second.SecondActivity
import com.suitmedia.question1.utils.NAME

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            if (binding.edPalindrome.text.toString().isEmpty()) {
                binding.edPalindrome.error = getString(R.string.this_field_cannot_be_empty)
            } else {
                val text = binding.edPalindrome.text.toString()
                val isPalindrome = checkPalindrome(text)
                if (isPalindrome) {
                    showDialog("isPalindrome")
                } else {
                    showDialog("not palindrome")
                }
            }
        }

        binding.btnNext.setOnClickListener {
            if (binding.edName.text.toString().isEmpty()) {
                binding.edName.error = getString(R.string.this_field_cannot_be_empty)
            } else {
                startActivity(
                    Intent(this, SecondActivity::class.java).apply {
                        putExtra(NAME, binding.edName.text.toString())
                    }
                )
            }
        }

    }

    private fun checkPalindrome(text: String) : Boolean {
        val cleanText = text.lowercase().replace("\\s".toRegex(),"")
        return cleanText == cleanText.reversed()
    }

    private fun showDialog(message: String) {
        val dialog = AlertDialog.Builder(this).apply {
            setMessage(message)
            setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
        }.create()
        dialog.show()
    }
}