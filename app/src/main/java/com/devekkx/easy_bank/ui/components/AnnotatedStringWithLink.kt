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
fun AnnotatedStringWithLink(
    label: String = "Text label",
    linkText: String = "Text Link",
    link: String = "www.google.com",
    modifier: Modifier = Modifier
) {
    Text(
        buildAnnotatedString {
            append("$label ")
            withLink(
                LinkAnnotation.Url(
                    link,
                    TextLinkStyles(
                        style = SpanStyle(
                            color = Primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                )
            ) {
                append(linkText)
            }
            append(".")
        }
    )
}