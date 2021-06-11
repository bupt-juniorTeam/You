package com.you.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.you.android.logic.model.WebSocketMessage
import com.you.android.util.LogUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChatRoomViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: ChatRoomViewModel

    lateinit var chatObserver: Observer<WebSocketMessage>

    lateinit var resObserver: Observer<Result<String>>

    @Before
    fun init() {
        viewModel = ChatRoomViewModel("TEST")
    }

    @Test
    fun testChat() {
        chatObserver = Observer {
            print("收到消息: $it")
        }

        resObserver = Observer {
            print("收到消息: $it")
        }

        viewModel.chatLiveData.observeForever(chatObserver)
        viewModel.joinRoomLiveData.observeForever(resObserver)

        viewModel.joinRoom()

        viewModel.sendMessage("111")

        viewModel.leaveRoom()
        viewModel.closeChat()
    }
}