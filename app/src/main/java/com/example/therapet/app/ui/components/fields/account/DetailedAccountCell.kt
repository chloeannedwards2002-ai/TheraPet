package com.example.therapet.app.ui.components.fields.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @Author: Chloe Edwards
 * @Date: 15/02/2026
 *
 * Detailed Account cell (reusable)
 */

@Composable
fun DetailedAccountCell(
    account: AccountUIModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onRemoveClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.92f)
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = account.fullName,
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = account.userid,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            onRemoveClick?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = it,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Remove from Watchlist",
                        style = MaterialTheme.typography.labelMedium)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = if (account.lastLoginMillis != null)
                    "Last active: ${formatLastActive(account.lastLoginMillis)}"
                else
                    "Never logged in",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun formatLastActive(timestamp: Long) : String{
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    val minutes = diff / (1000 * 60)
    val hours = diff / (1000 * 60 * 60)
    val days = diff / (1000 * 60 * 60 * 24)

    return when {
        minutes < 1 -> "Just now"
        minutes < 60 -> "$minutes minutes ago"
        hours < 24 -> "$hours hours ago"
        days < 7 -> "$days days ago"
        else -> {
            val date = java.text.SimpleDateFormat("dd MMM yyyy")
            date.format(java.util.Date(timestamp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailedAccountCellPreview() {

    val sampleAccount = AccountUIModel(
        userid = "123123123123",
        fullName = "Jane Doe",
        role = UserRole.PATIENT,
        lastLoginMillis = 1_800_000_000_000L
    )

    TheraPetTheme {
        DetailedAccountCell(
            account = sampleAccount
        )
    }
}

