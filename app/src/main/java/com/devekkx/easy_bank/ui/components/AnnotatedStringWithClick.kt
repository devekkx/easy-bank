package com.devekkx.easy_bank.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import com.devekkx.easy_bank.ui.theme.Primary

@Composable
fun AnnotatedStringWithClick(
    onClick: () -> Unit,
    label: String,
    linkText: String,
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        append("$label  ")
        val link =
            LinkAnnotation.Clickable(
                tag = linkText,
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = Primary,
                        fontWeight = FontWeight.Bold
                    )
                ), linkInteractionListener = {
                    onClick()
                }
            )
        withLink(link) { append(linkText) }
        append(".")
    }

    Text(text = annotatedString)
}