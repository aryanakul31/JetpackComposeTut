package com.nakul.template.ui.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nakul.template.R
import com.nakul.template.ui.ButtonGradient
import com.nakul.template.ui.CustomCheckBox
import com.nakul.template.ui.CustomTextField
import com.nakul.template.ui.TextHeading
import com.nakul.template.ui.TextSubHeading
import com.nakul.template.ui.fontFamily

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun LoginContentPreview() {
    LoginContent {}
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    email: MutableState<String> = mutableStateOf("aryanakul31@gmail.com"),
    emailError: String = "Incorrect Email",
    password: MutableState<String> = mutableStateOf("Test@123"),
    passwordError: String = "Incorrect Password",
    rememberMe: MutableState<Boolean> = mutableStateOf(true),
    clickSignIn: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp, 40.dp)
    ) {
        TextHeading(
            text = stringResource(R.string.welcome),
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
            font = fontFamily
        )
        TextSubHeading(
            text = stringResource(R.string.to_sign_in_please_enter_your_details_below),
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
            font = fontFamily
        )
        CustomTextField(
            modifier = Modifier.padding(0.dp, 40.dp, 0.dp, 0.dp),
            label = stringResource(R.string.email_address),
            value = email,
            errorMessage = emailError
        )
        CustomTextField(
            modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
            label = stringResource(R.string.password),
            value = password,
            errorMessage = passwordError
        )
        Row {
            CustomCheckBox(
                label = stringResource(R.string.remember_me),
                modifier = Modifier.weight(1f),
                font = fontFamily,
                checked = rememberMe,
            )
            TextSubHeading(
                text = stringResource(R.string.forgot_password),
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically),
                font = fontFamily,
                textAlign = TextAlign.End
            )
        }

        ButtonGradient(modifier = Modifier, label = "Sign In", onClick = {
            clickSignIn()
        })
    }
}