package com.pcsalt.example.sharedpreferencedemo

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.pcsalt.example.sharedpreferencedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var prefs: SharedPreferences
  private lateinit var adapter: ArrayAdapter<String>
  private val entries = mutableListOf<String>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    prefs = getSharedPreferences("demo_prefs", MODE_PRIVATE)

    adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, entries)
    binding.listView.adapter = adapter

    loadEntries()

    binding.btnSave.setOnClickListener {
      saveValue()
    }

    binding.listView.setOnItemClickListener { _, _, position, _ ->
      val key = entries[position].substringBefore(" = ")
      prefs.edit { remove(key) }
      loadEntries()
    }
  }

  private fun saveValue() {
    val key = binding.etKey.text.toString().trim()
    val value = binding.etValue.text.toString().trim()

    when {
      key.isEmpty() -> binding.etKey.error = "Enter key"
      value.isEmpty() -> binding.etValue.error = "Enter value"
      else -> {
        prefs.edit { putString(key, value) }
        binding.etKey.text.clear()
        binding.etValue.text.clear()
        loadEntries()
      }
    }
  }

  private fun loadEntries() {
    entries.clear()
    for ((key, value) in prefs.all) {
      entries.add("$key = $value")
    }
    adapter.notifyDataSetChanged()
  }
}
