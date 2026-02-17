package com.example.therapet.app.ui.components.fields.account

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.therapet.app.data.model.AccountUIModel

/**
 * @Author: Chloe Edwards
 * @Date: 15/02/2026
 *
 * Account cell dialog (popup)
 */

@Composable
fun AccountDetailsDialog(
    account: AccountUIModel?,
    onDismiss: () -> Unit
) {
    if (account == null) return

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            DetailedAccountCell(
                account = account,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}