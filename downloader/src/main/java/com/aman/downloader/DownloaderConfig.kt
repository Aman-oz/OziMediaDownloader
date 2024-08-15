package com.aman.downloader

import com.aman.downloader.utils.Constants

data class DownloaderConfig(
    var databaseEnabled: Boolean = false,
    var connectTimeOut: Int = Constants.DEFAULT_CONNECT_TIMEOUT_IN_MILLS,
    var readTimeOut: Int = Constants.DEFAULT_READ_TIMEOUT_IN_MILLS
)