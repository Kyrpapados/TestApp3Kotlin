package kyrpap.testapp3kotlin.data.model.responce

import kyrpap.testapp3kotlin.data.model.local.Trend
import com.google.gson.annotations.SerializedName

data class TrendsResponse (@SerializedName("items") var trends: List<Trend>)