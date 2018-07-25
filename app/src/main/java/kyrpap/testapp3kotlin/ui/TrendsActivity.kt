package kyrpap.testapp3kotlin.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_trends.*
import kyrpap.testapp3kotlin.R
import kyrpap.testapp3kotlin.data.model.local.Trend
import kyrpap.testapp3kotlin.data.model.responce.TrendsResponse
import kyrpap.testapp3kotlin.data.network.TrendsApiClient
import kyrpap.testapp3kotlin.data.network.TrendsApiInterface
import kyrpap.testapp3kotlin.ui.details.TrendDetailsActivity

class TrendsActivity : AppCompatActivity(), TrendAdapter.Listener  {

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mTrendList: List<Trend>? = null

    private var mAdapter: TrendAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trends)

        var mLayoutManager = LinearLayoutManager(this)
        trendsRecyclerView.layoutManager = mLayoutManager
        trendsRecyclerView.itemAnimator = DefaultItemAnimator()
        trendsRecyclerView.addItemDecoration(DividerItemDecoration(trendsRecyclerView.context, mLayoutManager.orientation))
        mCompositeDisposable = CompositeDisposable()

        val apiCLient = TrendsApiClient.create()

        mCompositeDisposable?.add(apiCLient.getTrends()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))


    }

    private fun handleResponse(trendList: TrendsResponse) {

        mTrendList = trendList.trends

        mAdapter = TrendAdapter(this, mTrendList!!, this)

        trendsRecyclerView.adapter = mAdapter
    }

    private fun handleError(error: Throwable) {

        Log.d("TAG", error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(trend: Trend) {
        val intent = Intent(this, TrendDetailsActivity::class.java)
        intent.putExtra("TREND", trend)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}
