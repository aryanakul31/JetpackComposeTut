package com.nakul.template.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nakul.template.R
import com.nakul.template.ui.theme.Colors

val fontFamily = FontFamily(
    Font(R.font.bw_modelica, FontWeight.Normal),
    Font(R.font.bw_modelica_bold, FontWeight.Bold),
    Font(R.font.bw_modelica_medium, FontWeight.Medium),
)

@Composable
fun CustomCheckBox(
    modifier: Modifier,
    label: String,
    font: FontFamily,
    checked: MutableState<Boolean>
) {
    Row(modifier = modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = {
                checked.value = it
            },
            modifier = Modifier
        )
        TextSubHeading(
            text = label,
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            font = font
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier,
    label: String,
    value: MutableState<String>,
    errorMessage: String = "",
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value.value,
        isError = errorMessage.isNotBlank(),
        onValueChange = {
            value.value = it
        },
        supportingText = {
            if (errorMessage.isBlank())
                return@TextField

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.primary.copy(0.25f)
        ),
        label = {
            Text(text = label)
        })
}


@Composable
fun TextHeading(text: String, modifier: Modifier, font: FontFamily) {
    Text(
        text = text, modifier = modifier, style = TextStyle(
            color = if (isSystemInDarkTheme()) Colors.textColorDark else Colors.textColor,
            fontSize = 26.sp,
            fontFamily = font,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun TextSubHeading(
    text: String,
    modifier: Modifier,
    font: FontFamily,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text, modifier = modifier, style = TextStyle(
            color = if (isSystemInDarkTheme()) Colors.textColorDark else Colors.textColor,
            fontSize = 14.sp,
            fontFamily = font,
            fontWeight = FontWeight.Normal
        ), textAlign = textAlign
    )
}

@Composable
fun ButtonGradient(modifier: Modifier, label: String, onClick: () -> Unit) {
    ElevatedButton(
        elevation = ButtonDefaults.buttonElevation(10.dp),
        modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = onClick
    ) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.50f),
                        )
                    )
                )
                .padding(10.dp),
            textAlign = TextAlign.Center,
        )
    }
}