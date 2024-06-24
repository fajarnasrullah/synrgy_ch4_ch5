package com.jer.ch4_ch5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jer.ch4_ch5.MyApplication
import com.jer.ch4_ch5.R
import com.jer.ch4_ch5.data.datasource.local.room.UserNote
import com.jer.ch4_ch5.databinding.ActivityFormNoteBinding

class FormNoteActivity : AppCompatActivity() {

    private  var _binding: ActivityFormNoteBinding? = null
    private val binding get() = _binding

//    private lateinit var viewModel: NoteViewModel
    private val viewModel by viewModels<NoteViewModel> {
    (application as MyApplication).viewModelFactory
    }
    companion object {
        const val EXTRA_NOTE = "extra_note"

    }
    private var isEdit = false
    private var note: UserNote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFormNoteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        viewModel = obtainViewModel(this@FormNoteActivity)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            isEdit = true
        } else {
            note = UserNote()
        }

        val btnTitle: String

        if (isEdit) {
            btnTitle = "Update"
            if (note != null) {
                note?.let {
                    binding?.edtTitle?.setText(it.nama)
                    binding?.edtCity?.setText(it.asal)
                    binding?.edtMajor?.setText(it.prodi)
                }
            }
        } else {
            btnTitle = "Save"
        }

        binding?.btnSubmit?.text = btnTitle


        binding?.btnDelete?.setOnClickListener {

            val intent = Intent(this@FormNoteActivity, HomeNoteActivity::class.java)
            startActivity(intent)
            if (isEdit) {
                viewModel.delete(note as UserNote)
                Toast.makeText(this, "You removed one item", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.btnSubmit?.setOnClickListener {
            val name = binding?.edtTitle?.text.toString().trim()
            val city = binding?.edtCity?.text.toString().trim()
            val major = binding?.edtMajor?.text.toString().trim()

            when {
                name.isEmpty() -> {
                    binding?.edtTitle?.error = "This section cant be empty"
                }

                city.isEmpty() -> {
                    binding?.edtCity?.error = "This section cant be empty"
                }

                major.isEmpty() -> {
                    binding?.edtMajor?.error = "This section cant be empty"
                }

                else -> {
                    note.let {
                        it?.nama = name
                        it?.asal = city
                        it?.prodi = major

                        val intent = Intent(this@FormNoteActivity, HomeNoteActivity::class.java)
                        startActivity(intent)
                    }

                    if (isEdit) {
                        viewModel.update(note as UserNote)
                        Toast.makeText(this, "You changes one item", Toast.LENGTH_SHORT).show()

                    } else {

                        note.let { note ->
                            note?.date = DateHelper.getCurrentDate()


                            viewModel.insert(note as UserNote)
                            Toast.makeText(this, "Succeed to add new item", Toast.LENGTH_SHORT)
                                .show()
                        }
                        finish()
                    }

                }
            }


        }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun obtainViewModel(activity: AppCompatActivity): NoteViewModel {
        val factory = VMFactoryRoom.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(NoteViewModel::class.java)
    }


}