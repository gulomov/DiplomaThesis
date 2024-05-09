package com.diploma.work.introduction.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.introduction.R

@Composable
internal fun IntroductionContent(
    page: Int,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column {
            val imageResources =
                when (page) {
                    0 -> R.drawable.ic_intro_one
                    1 -> R.drawable.ic_intro_two
                    2 -> R.drawable.ic_intro_three
                    else -> 0
                }

            val textTitle =
                when (page) {
                    0 -> R.string.introduction_welcome_title
                    1 -> R.string.introduction_sales_info_title
                    2 -> R.string.introduction_enjoy_your_time_title
                    else -> 0
                }

            val textBody =
                when (page) {
                    0 -> R.string.introduction_welcome_text
                    1 -> R.string.introduction_sales_info_text
                    2 -> R.string.introduction_enjoy_your_time_text
                    else -> 0
                }

            Spacer(modifier = Modifier.height(normal100))
            Image(
                painter = painterResource(id = imageResources),
                contentDescription = null,
                modifier = Modifier.padding(start = small100),
            )
            Spacer(modifier = Modifier.height(normal100))
            Text(
                text = stringResource(id = textTitle),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(normal100))
            Text(
                text = stringResource(id = textBody),
                textAlign = TextAlign.Center,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = normal100, end = normal100),
            )
            Spacer(modifier = Modifier.height(normal100))
        }
    }
}