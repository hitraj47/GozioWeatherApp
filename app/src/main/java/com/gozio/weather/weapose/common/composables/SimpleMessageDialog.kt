package com.gozio.weather.weapose.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gozio.weather.weapose.R

@Composable
fun SimpleMessageDialog(
    message: String,
    title: String = stringResource(id = R.string.default_error_title),
    onDismissDialog: () -> Unit = {}
) {
    var openDialog by remember { mutableStateOf(true) }

    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            Box(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = title, style = MaterialTheme.typography.titleLarge)
                    Text(text = message)
                    Button(modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            openDialog = false
                            onDismissDialog.invoke()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.default_dismiss_button))
                    }
                }
            }
        }
    }
}