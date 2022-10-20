package com.example.no_context.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import com.example.no_context.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.viewmodel.channels.ChannelListViewModel

@AndroidEntryPoint
class ChannelListActivity : ComponentActivity() {

    val viewModel: ChannelListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ChatTheme {

                var showDialog: Boolean by remember {
                    mutableStateOf(false)
                }

//                if (showDialog) {
//                    CreateChannelDialog(
//                        dismiss = { channelName ->
//                            viewModel.createChannel(channelName)
//                            showDialog = false
//                        }
//                    )
//                }

                ChannelsScreen(
                    isShowingSearch = false,
                    isShowingHeader = false,
                    filters = Filters.`in`(
                        fieldName = "type",
                        values = listOf("gaming", "messaging", "commerce", "team", "livestream")
                    ),
                    title = "Channel List",
                    onItemClick = { channel ->
                        startActivity(MessagesActivity.getIntent(this, channelId = channel.cid))
                    },
                    onBackPressed = { finish() },

//                    onHeaderAvatarClick = {
//                        viewModel.logout()
//                        finish()
//                        startActivity(Intent(this, LoginActivity::class.java))
//                    }
                )
            }
        }
    }


    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
