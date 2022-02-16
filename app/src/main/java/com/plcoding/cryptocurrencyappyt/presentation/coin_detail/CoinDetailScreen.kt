package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import android.graphics.PointF
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components.CoinTag
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components.TeamListItem

@Preview
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel= hiltViewModel(),
    historicalviewModel:CoinHistoricalPriceViewModel=hiltViewModel()
){
    val state=viewModel.stateCoinDetail.value
    val historical_state=historicalviewModel.stateCoinHistorical.value

    Box(modifier=Modifier.fillMaxSize()){
        LineChartTheme {
            Surface(color = Color.Black){//MaterialTheme.colors.background) {
                Scaffold(

                ) {
                    LineChart(historical_state)
                }
            }
        }
        state.coin?.let { coin->
            LazyColumn(
                modifier=Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ){
               item{
                   Row(
                       modifier=Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween
                   ){
                       Spacer(modifier = Modifier.height(300.dp))
                       Text(
                           text="${coin.rank}. ${coin.name} (${coin.symbol})",
                           style = MaterialTheme.typography.h2,
                           modifier = Modifier.weight(8f)
                       )
                       Text(
                           text = if(coin.isActive)"active" else "inactive",
                           color=if(coin.isActive) Color(154,200,0) else Color(200,0,0),
                           fontStyle = FontStyle.Italic,
                           textAlign = TextAlign.End,
                           style = MaterialTheme.typography.body2,
                           modifier = Modifier
                               .align(Alignment.CenterVertically)
                               .weight(2f)
                       )
                   }
                   Spacer(modifier = Modifier.height(15.dp))
                   //copyzott

                   //eddig
                   Spacer(modifier = Modifier.height(15.dp))
                   Text(
                       text=coin.description,
                       style = MaterialTheme.typography.body2
                   )
                   Spacer(modifier = Modifier.height(15.dp))
                   Text(
                       text="Tags",
                       style = MaterialTheme.typography.h3
                   )
                   Spacer(modifier = Modifier.height(15.dp))
                   FlowRow(
                       mainAxisSpacing = 10.dp,
                       crossAxisSpacing = 10.dp,
                       modifier = Modifier.fillMaxWidth()
                   ) {
                       coin.tags.forEach{tag->
                           CoinTag(tag = tag)
                       }
                   }
                   Spacer(modifier = Modifier.height(15.dp))
                   Text(
                       text="Team Members",
                       style = MaterialTheme.typography.h3
                   )
                   Spacer(modifier = Modifier.height(15.dp))
               }
                items(coin.team){TeamMember ->
                    TeamListItem(
                        teamMember = TeamMember,
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()

                }
            }
        }
        if(state.error.isNotBlank()){
            Text(
                text=state.error,
                color= MaterialTheme.colors.error,
                textAlign= TextAlign.Center,
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading){
            CircularProgressIndicator(modifier=Modifier.align(Alignment.Center))
        }
    }
}



//copyzott


val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)
private val LightColorPalette = lightColors(
    onPrimary = Color.Black,
    //primaryVariant = Purple700,
    onSecondary = Color.Black

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)
private val DarkColorPalette = darkColors(
    onPrimary = Color.Black,
    //primaryVariant = Purple700,
    onSecondary = Color.Black
)

@Composable
fun LineChartTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
      //  colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

//Static value
private fun lineChartData() = listOf(
    5929, 6898, 8961, 5674, 7122, 6592, 3427, 5520, 4680, 7418,

)
private fun linechartdata(historical_state:CoinHistoricalPriceState)=listOf(historical_state.coinHistoricalPrice
)

@Composable
fun LineChart(historical_state:CoinHistoricalPriceState) {
    var chartData: MutableList<Double> = emptyList<Double>().toMutableList()
    val historical_stateData= linechartdata(historical_state)

    Log.d("ASD", historical_state.toString())

    if(historical_state.coinHistoricalPrice?.isNotEmpty() == true) {
        for (i in historical_state.coinHistoricalPrice.indices) {
            Log.d("pina2", i.toString())
            chartData.add(i, historical_state.coinHistoricalPrice[i].open)
        }
    }
    Log.d("pina2", chartData.size.toString())
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .padding(16.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(align = Alignment.BottomStart)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                val distance = size.width / (chartData.size + 1)
                var currentX = 0F
                val maxValue:Double =
                    (chartData.maxOrNull()?:0).toDouble()//lineChartData().maxOrNull() ?: 0
                val points = mutableListOf<PointF>()

                chartData.forEachIndexed { index, data ->
                    if (chartData.size >= index + 2) {
                        val y0 = (maxValue - data) * (size.height / maxValue)
                        val x0 = currentX + distance
                        points.add(PointF(x0, y0.toFloat()))
                        currentX += distance
                    }
                }

                for (i in 0 until points.size - 1) {
                    drawLine(
                        start = Offset(points[i].x, points[i].y),
                        end = Offset(points[i + 1].x, points[i + 1].y),
                        color = Color(0xFFFF0000),
                        strokeWidth = 8f
                    )
                }
            }
        }
    }
}

