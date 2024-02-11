import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateBizCard()
        }
    }
}

@Composable
fun CreateBizCard() {

    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {

            Column(modifier = Modifier.width(390.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider()
                CreateInfo()
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                }) {
                    Text(text = "Portfolio")
                }
                if (buttonClickedState.value) {
                    Content()
                }
                else
                {
                    Box {}
                }
            }

        }
    }
}

@Preview
@Composable
fun Content() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)
    ) {
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(
                name = listOf(
                    mapOf(
                        "name" to "Anya Forger",
                        "desc" to "Child, Telepath",
                        "img_id" to R.drawable.anya_new
                    ),
                    mapOf(
                        "name" to "Loid Forger",
                        "desc" to "Father, Spy",
                        "img_id" to R.drawable.loid
                    ),
                    mapOf(
                        "name" to "Yor Forger",
                        "desc" to "Mother, Assassin",
                        "img_id" to R.drawable.yor
                    ),
                    mapOf(
                        "name" to "Bond Forger",
                        "desc" to "Dog, Can watch Future",
                        "img_id" to R.drawable.bond
                    )
                )
            )
        }
    }
}

@Composable
fun Portfolio(name: List<Map<String, Any>>) {
    LazyColumn {
        items(name)  { item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape
            ) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
                    .fillMaxWidth()) {

                    CreateImageProfile(modifier = Modifier.size(100.dp), item["img_id"] as Int)

                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)) {
                        Text(text = item["name"].toString(), fontWeight = FontWeight.Bold)
                        Text(text = item["desc"].toString(), style = MaterialTheme.typography.bodySmall)
                    }

                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Anya Forger",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(text = "Telepath, Student", modifier = Modifier.padding(3.dp))

        Text(
            text = "@starlightanya", modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier, image_id: Int = R.drawable.spy_x_family) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = image_id),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CreateBizCard()
}
