package com.ar.contactdemo

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


private val PROJECTION = arrayOf(
    ContactsContract.CommonDataKinds.Phone.NUMBER
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContactList()
        setContentView(R.layout.activity_main)
    }
    private fun getContactList() {
        val cr = contentResolver
        val cursor: Cursor? = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            PROJECTION,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        if (cursor != null) {
            val mobileNoSet = ArrayList<String>()

            try {
                val numberIndex: Int =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                var number: String
                while (cursor.moveToNext()) {
                    number = cursor.getString(numberIndex)
                    val regex = Regex("[^\\d]")
                    val formatedNumber = regex.replace(number,"")

                    if (!mobileNoSet.contains(number)) {
                        mobileNoSet.add(formatedNumber)
                        Log.d(
                            "hvy", "onCreaterrView  Phone Number: "
                                    + " No = " + formatedNumber
                        )
                    }
                }
            } finally {
                cursor.close()
            }
        }
    }
}