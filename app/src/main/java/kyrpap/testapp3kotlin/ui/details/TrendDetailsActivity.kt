package kyrpap.testapp3kotlin.ui.details

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_trend_details.*
import kotlinx.android.synthetic.main.item_trend.*
import kyrpap.testapp3kotlin.R
import kyrpap.testapp3kotlin.data.model.local.Trend

class TrendDetailsActivity : AppCompatActivity() {

    fun show(activity: Activity, trend: Trend) {
        val intent = Intent(activity, TrendDetailsActivity::class.java)
        intent.putExtra("TREND", trend)
        activity.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trend_details)
        var trend = intent.getParcelableExtra<Trend>("TREND")

        Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions().override(200, 200))
                .load(trend.owner.avatar)
                .into(userThumb)

        titleGit.text = trend.name
        owner.text = trend.owner.login
        description.text = trend.description
    }
}
