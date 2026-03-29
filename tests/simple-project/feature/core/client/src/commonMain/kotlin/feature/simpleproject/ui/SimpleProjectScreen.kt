package feature.simpleproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.enro.annotations.NavigationDestination

@Composable
@NavigationDestination(SimpleProjectDestination::class)
fun SimpleProjectScreen() {
    Column {
        Text(
            text = "Simple Project Screen",
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = "This is a simple project screen for the UKPT project template",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}