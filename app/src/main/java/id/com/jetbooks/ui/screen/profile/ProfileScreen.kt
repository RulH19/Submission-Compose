package id.com.jetbooks.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.com.jetbooks.R
import id.com.jetbooks.ui.theme.JetBooksTheme

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.padding(80.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.foto_formal),
            contentDescription = "Profile_Page",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Nurul Hidayatullah",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Nurulh298hd@gmail.com",
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold
            )

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JetBooksTheme {
        ProfileScreen()
    }
}