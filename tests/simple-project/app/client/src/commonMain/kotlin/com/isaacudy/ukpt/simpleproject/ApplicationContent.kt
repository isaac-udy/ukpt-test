package com.isaacudy.ukpt.simpleproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.enro.asInstance
import dev.enro.backstackOf
import dev.enro.ui.NavigationDisplay
import dev.enro.ui.rememberNavigationContainer
import feature.simpleproject.ui.SimpleProjectDestination

@Composable
fun ApplicationContent() {
    val rootNavigationContainer = rememberNavigationContainer(
        backstack = backstackOf(SimpleProjectDestination.asInstance()),
    )
    NavigationDisplay(
        state = rootNavigationContainer,
        modifier = Modifier.fillMaxSize(),
    )
}