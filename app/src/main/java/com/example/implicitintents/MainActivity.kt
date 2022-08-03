package com.example.implicitintents

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat


class MainActivity : AppCompatActivity() {

    private var mWebsiteEditText: EditText? = null
    private var mLocationEditText: EditText? = null
    private var mShareTextEditText: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWebsiteEditText =
            findViewById(R.id.website_edittext) //чого в onCreate а не в fun openWebSite ???
        mLocationEditText = findViewById(R.id.location_edittext)
        mShareTextEditText = findViewById(R.id.share_edittext)
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun openWebSite(view: View) {
        val url = mWebsiteEditText?.text.toString()
        val webpage: Uri = Uri.parse(url)
        val intent =
            Intent(Intent.ACTION_VIEW, webpage) // можна запхати Uri.parse(url) замість webpage?
        if (intent.resolveActivity(packageManager) != null) { //це тіпа якщо якась приложуха для откриття установлена (!= null) то startActivity(intent)??
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this!")
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun openLocation(view: View) {
        val loc = mLocationEditText?.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    fun shareText(view: View) {
        val txt = mShareTextEditText?.text.toString()
        val mimeType = "text/plain"
        ShareCompat
            .IntentBuilder(this) //тепер я знаю шо закреслекний ".from(this)" устарів)))))
            .setType(mimeType)
            .setChooserTitle("Share this text with: ")
            .setText(txt)
            .startChooser()
    }
}