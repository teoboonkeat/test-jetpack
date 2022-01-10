package sg.com.sph.activityresultscontracttest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.material.Text

class SubActivity : AppCompatActivity() {

    companion object {
        val RETURN_KEY: String = "sub-activity-return-key"

        fun launch(context: Context) {
            val registry = (context as AppCompatActivity).activityResultRegistry
            val test = registry.register("test-key", SubActivityContract()) {
                // Handle results
                it?.let {
                    println("Results from SubActivity finish: ${it.info}")
                }

            }

            test.launch(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sub)

        setContent {
            Text("Hello this is Compose")
        }
        val data = SubActData(
            info = "hello from SUbactivity"
        )

        val returnIntent = Intent().apply {
            putExtra(RETURN_KEY, data)
        }
        setResult(Activity.RESULT_OK, returnIntent)
//        finish()
    }
}

class SubActivityContract: ActivityResultContract<Int, SubActData?>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, SubActivity::class.java)
    }


    override fun parseResult(resultCode: Int, intent: Intent?): SubActData? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }
        return intent?.getParcelableExtra(SubActivity.RETURN_KEY)
    }

}