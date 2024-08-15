package com.aman.ozimediadownloader

import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aman.downloader.OziDownloader
import com.aman.downloader.Status
import com.aman.ozimediadownloader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //***********************************************************************************************//
    private val TAG = MainActivity::class.java.simpleName

    //***********************************************************************************************//
    private lateinit var dirPath: String
    private var downloadId0 = 0
    private var downloadId1 = 0
    private var downloadId2 = 0
    private var downloadId3 = 0
    private var downloadId4 = 0
    private var downloadId5 = 0
    private var downloadId6 = 0
    private var downloadId7 = 0
    private var downloadId8 = 0

    //*******************************************ozi downloader**************************************//
    private lateinit var oziDownloader: OziDownloader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initObjects()
        makeRequests()

    }

    //**************************************init objects******************************//
    private fun initObjects() {
        oziDownloader = (applicationContext as AppClass).oziDownloader
        dirPath = Environment.getExternalStorageDirectory().path + "/Download"
    }

    private fun makeRequests() {
        //***************image request****************//
        val request0 =
            oziDownloader.newRequestBuilder(
                url = "https://images.unsplash.com/photo-1719937050445-098888c0625e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxmZWF0dXJlZC1waG90b3MtZmVlZHw2fHx8ZW58MHx8fHx8",
                dirPath = dirPath,
                fileName = "file.png"
            ).tag(TAG + "0").build()

        //***************gif request****************//

        val request1 =
            oziDownloader.newRequestBuilder(
                url = "https://media4.giphy.com/media/l0HFkA6omUyjVYqw8/200.webp?cid=82a1493b8l4gb5ffkkecjlbttng5jc36tn0s5ea96dwqy4g6&ep=v1_gifs_trending&rid=200.webp&ct=g",
                dirPath = dirPath,
                fileName = "file.gif"
            )
                .tag(TAG + "1").build()

        //***************mp3 request****************//
        val request2 =
            oziDownloader.newRequestBuilder(
                url = "https://github.com/rafaelreis-hotmart/Audio-Sample-files/raw/master/sample.mp3",
                dirPath = dirPath,
                fileName = "file.mp3"
            )
                .tag(TAG + "2").build()

        //***************mp4 request****************//
        val request3 =
            oziDownloader.newRequestBuilder(
                url = "https://drive.google.com/file/d/1gspFwVeCDVelZNOmUWk4hlSbfQvR5QlR/view?usp=sharing",
                dirPath = dirPath,
                fileName = "file.mp4"
            )
                .tag(TAG + "3").build()

        //***************bin request****************//
        val request4 =
            oziDownloader.newRequestBuilder(
                url = "https://docs.google.com/document/d/1xKkGzYVFotJDT_GMB9jVa9FlEIJYsgtB_X8j-Lry0lk/edit?usp=sharing",
                dirPath = dirPath,
                fileName = "file.docx"
            )
                .tag(TAG + "4").build()

        //***************pdf request****************//
        val request5 =
            oziDownloader.newRequestBuilder(
                url = "https://s29.q4cdn.com/175625835/files/doc_downloads/test.pdf",
                dirPath = dirPath,
                fileName = "file.pdf"
            )
                .tag(TAG + "5").build()

        //***************apk request****************//
        val request6 =
            oziDownloader.newRequestBuilder(
                url = "https://drive.google.com/file/d/1Cts7DAHXzhbowFbxGIUwt17xgItC6zHF/view?usp=sharing",
                dirPath = dirPath,
                fileName = "file.apk"
            )
                .tag(TAG + "6").build()

        //****************rar request*****************//

        val request7 =
            oziDownloader.newRequestBuilder(
                url = "https://drive.google.com/file/d/1xnLhD7J4aZcjkzBR-0a_D1Ww8N6L9l-l/view?usp=sharing",
                dirPath = dirPath,
                fileName = "file.rar"
            ).tag(TAG + "7").build()

        //****************zip request*****************//

        val request8 =
            oziDownloader.newRequestBuilder(
                url = "https://drive.google.com/file/d/1R2YDiHrw-XkBk5yKPboGsSGuJGztlkO2/view?usp=sharing",
                dirPath = dirPath,
                fileName = "file.zip"
            ).tag(TAG + "8").build()

        //***********************do request listeners**************************//

        //***************image request****************//
        binding.fileName0.text = "file.png"
        binding.startCancelButton0.setOnClickListener {
            if (binding.startCancelButton0.text.equals("Start")) {
                downloadId0 = oziDownloader.enqueue(request0,
                    onStart = {
                        binding.status0.text = "Started"
                        binding.startCancelButton0.text = "Cancel"
                        binding.resumePauseButton0.isEnabled = true
                        binding.resumePauseButton0.visibility = View.VISIBLE
                        binding.resumePauseButton0.text = "Pause"
                    },
                    onProgress = {
                        binding.status0.text = "In Progress"
                        binding.progressBar0.progress = it
                        binding.progressText0.text = "$it%"
                    },
                    onCompleted = {
                        binding.status0.text = "Completed"
                        binding.progressText0.text = "100%"
                        binding.startCancelButton0.visibility = View.GONE
                        binding.resumePauseButton0.visibility = View.GONE
                    },
                    onError = {
                        binding.status0.text = "Error : $it"
                        binding.resumePauseButton0.visibility = View.GONE
                        binding.progressBar0.progress = 0
                        binding.progressText0.text = "0%"
                    },
                    onPause = {
                        binding.status0.text = "Paused"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId0)
                binding.startCancelButton0.text = "Start"
            }
        }

        binding.resumePauseButton0.setOnClickListener {
            if (oziDownloader.status(downloadId0) == Status.PAUSED) {
                binding.resumePauseButton0.text = "Pause"
                oziDownloader.resume(downloadId0)
            } else {
                binding.resumePauseButton0.text = "Resume"
                oziDownloader.pause(downloadId0)
            }
        }

        //***************gif request****************//

        binding.fileName1.text = "file.gif"
        binding.startCancelButton1.setOnClickListener {
            if (binding.startCancelButton1.text.equals("Start")) {
                downloadId1 = oziDownloader.enqueue(request1,
                    onStart = {
                        binding.status1.text = "Started"
                        binding.startCancelButton1.text = "Cancel"
                        binding.resumePauseButton1.isEnabled = true
                        binding.resumePauseButton1.visibility = View.VISIBLE
                        binding.resumePauseButton1.text = "Pause"
                    },
                    onProgress = {
                        binding.status1.text = "In Progress"
                        binding.progressBar1.progress = it
                        binding.progressText1.text = "$it%"
                    },
                    onCompleted = {
                        binding.status1.text = "Completed"
                        binding.progressText3.text = "100%"
                        binding.startCancelButton1.visibility = View.GONE
                        binding.resumePauseButton1.visibility = View.GONE
                    },
                    onError = {
                        binding.status1.text = "Error : $it"
                        binding.resumePauseButton1.visibility = View.GONE
                        binding.progressBar1.progress = 0
                        binding.progressText1.text = "0%"
                    },
                    onPause = {
                        binding.status1.text = "Paused"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId1)
                binding.startCancelButton1.text = "Start"
            }
        }

        binding.resumePauseButton1.setOnClickListener{
            if (oziDownloader.status(downloadId1) == Status.PAUSED) {
                binding.resumePauseButton1.text = "Pause"
                oziDownloader.resume(downloadId1)
            } else {
                binding.resumePauseButton1.text = "Resume"
                oziDownloader.pause(downloadId1)
            }
        }

        //***************mp3 request****************//
        binding.fileName2.text = "file.mp3"
        binding.startCancelButton2.setOnClickListener {
            if (binding.startCancelButton2.text.equals("Start")) {
                downloadId2 = oziDownloader.enqueue(request2,
                    onStart = {
                        binding.status2.text = "Started"
                        binding.startCancelButton2.text = "Cancel"
                        binding.resumePauseButton2.isEnabled = true
                        binding.resumePauseButton2.visibility = View.VISIBLE
                        binding.resumePauseButton2.text = "Pause"
                    },
                    onProgress = {
                        binding.status2.text = "In Progress"
                        binding.progressBar2.progress = it
                        binding.progressText2.text = "$it%"
                    },
                    onCompleted = {
                        binding.status2.text = "Completed"
                        binding.progressText3.text = "100%"
                        binding.startCancelButton2.visibility = View.GONE
                        binding.resumePauseButton2.visibility = View.GONE
                    },
                    onError = {
                        binding.status2.text = "Error : $it"
                        binding.resumePauseButton2.visibility = View.GONE
                        binding.progressBar2.progress = 0
                        binding.progressText2.text = "0%"
                    },
                    onPause = {
                        binding.status2.text = "Paused"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId2)
                binding.startCancelButton2.text = "Start"
            }
        }

        binding.resumePauseButton2.setOnClickListener{
            if (oziDownloader.status(downloadId2) == Status.PAUSED) {
                binding.resumePauseButton2.text = "Pause"
                oziDownloader.resume(downloadId2)
            } else {
                binding.resumePauseButton2.text = "Resume"
                oziDownloader.pause(downloadId2)
            }
        }

        //***************mp4 request****************//
        binding.fileName3.text = "file.mp4"
        binding.startCancelButton3.setOnClickListener {
            if (binding.startCancelButton3.text.equals("Start")) {
                downloadId3 = oziDownloader.enqueue(request3,
                    onStart = {
                        binding.status3.text = "Started"
                        binding.startCancelButton3.text = "Cancel"
                        binding.resumePauseButton3.isEnabled = true
                        binding.resumePauseButton3.visibility = View.VISIBLE
                    },
                    onPause = {
                        binding.status3.text = "Paused"
                    },
                    onProgress = {
                        binding.status3.text = "In Progress"
                        binding.progressBar3.progress = it
                        binding.progressText3.text = "$it%"
                    },
                    onCompleted = {
                        binding.status3.text = "Completed"
                        binding.progressText3.text = "100%"
                        binding.startCancelButton3.visibility = View.GONE
                        binding.resumePauseButton3.visibility = View.GONE
                    },
                    onError = {
                        binding.status3.text = "Error : $it"
                        binding.resumePauseButton3.visibility = View.GONE
                        binding.progressBar3.progress = 0
                        binding.progressText3.text = "0%"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId3)
                binding.startCancelButton3.text = "Start"
            }
        }

        binding.resumePauseButton3.setOnClickListener{
            if (oziDownloader.status(downloadId3) == Status.PAUSED) {
                binding.resumePauseButton3.text = "Pause"
                oziDownloader.resume(downloadId3)
            } else {
                binding.resumePauseButton3.text = "Resume"
                oziDownloader.pause(downloadId3)
            }
        }

        //***************bin request****************//

        binding.fileName4.text = "file.docx"
        binding.startCancelButton4.setOnClickListener {
            if (binding.startCancelButton4.text.equals("Start")) {
                downloadId4 = oziDownloader.enqueue(request4,
                    onStart = {
                        binding.status4.text = "Started"
                        binding.startCancelButton4.text = "Cancel"
                        binding.resumePauseButton4.isEnabled = true
                        binding.resumePauseButton4.visibility = View.VISIBLE
                    },
                    onPause = {
                        binding.status4.text = "Paused"
                    },
                    onProgress = {
                        binding.status4.text = "In Progress"
                        binding.progressBar4.progress = it
                        binding.progressText4.text = "$it%"
                    },
                    onCompleted = {
                        binding.status4.text = "Completed"
                        binding.progressText4.text = "100%"
                        binding.startCancelButton4.visibility = View.GONE
                        binding.resumePauseButton4.visibility = View.GONE
                    },
                    onError = {
                        binding.status4.text = "Error : $it"
                        binding.resumePauseButton4.visibility = View.GONE
                        binding.progressBar4.progress = 0
                        binding.progressText4.text = "0%"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId4)
                binding.startCancelButton4.text = "Start"
            }
        }

        binding.resumePauseButton4.setOnClickListener{
            if (oziDownloader.status(downloadId4) == Status.PAUSED) {
                binding.resumePauseButton4.text = "Pause"
                oziDownloader.resume(downloadId4)
            } else {
                binding.resumePauseButton4.text = "Resume"
                oziDownloader.pause(downloadId4)
            }
        }

        //***************pdf request****************//

        binding.fileName5.text = "file.pdf"
        binding.startCancelButton5.setOnClickListener {
            if (binding.startCancelButton5.text.equals("Start")) {
                downloadId5 = oziDownloader.enqueue(request5,
                    onStart = {
                        binding.status5.text = "Started"
                        binding.startCancelButton5.text = "Cancel"
                        binding.resumePauseButton5.isEnabled = true
                        binding.resumePauseButton5.visibility = View.VISIBLE
                    },
                    onPause = {
                        binding.status5.text = "Paused"
                    },
                    onProgress = {
                        binding.status5.text = "In Progress"
                        binding.progressBar5.progress = it
                        binding.progressText5.text = "$it%"
                    },
                    onCompleted = {
                        binding.status5.text = "Completed"
                        binding.progressText5.text = "100%"
                        binding.startCancelButton5.visibility = View.GONE
                        binding.resumePauseButton5.visibility = View.GONE
                    },
                    onError = {
                        binding.status5.text = "Error : $it"
                        binding.resumePauseButton5.visibility = View.GONE
                        binding.progressBar5.progress = 0
                        binding.progressText5.text = "0%"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId5)
                binding.startCancelButton5.text = "Start"
            }
        }

        binding.resumePauseButton5.setOnClickListener {
            if (oziDownloader.status(downloadId5) == Status.PAUSED) {
                binding.resumePauseButton5.text = "Pause"
                oziDownloader.resume(downloadId5)
            } else {
                binding.resumePauseButton5.text = "Resume"
                oziDownloader.pause(downloadId5)
            }
        }

        //***************apk request****************//

        binding.fileName6.text = "file.apk"
        binding.startCancelButton6.setOnClickListener {
            if (binding.startCancelButton6.text.equals("Start")) {
                downloadId6 = oziDownloader.enqueue(request6,
                    onStart = {
                        binding.status6.text = "Started"
                        binding.startCancelButton6.text = "Cancel"
                        binding.resumePauseButton6.isEnabled = true
                        binding.resumePauseButton6.visibility = View.VISIBLE
                    },
                    onPause = {
                        binding.status6.text = "Paused"
                    },
                    onProgress = {
                        binding.status6.text = "In Progress"
                        binding.progressBar6.progress = it
                        binding.progressText6.text = "$it%"
                    },
                    onCompleted = {
                        binding.status6.text = "Completed"
                        binding.progressText6.text = "100%"
                        binding.startCancelButton6.visibility = View.GONE
                        binding.resumePauseButton6.visibility = View.GONE
                    },
                    onError = {
                        binding.status6.text = "Error : $it"
                        binding.resumePauseButton6.visibility = View.GONE
                        binding.progressBar6.progress = 0
                        binding.progressText6.text = "0%"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId6)
                binding.startCancelButton6.text = "Start"
            }
        }

        binding.resumePauseButton6.setOnClickListener{
            if (oziDownloader.status(downloadId6) == Status.PAUSED) {
                binding.resumePauseButton6.text = "Pause"
                oziDownloader.resume(downloadId6)
            } else {
                binding.resumePauseButton6.text = "Resume"
                oziDownloader.pause(downloadId6)
            }
        }

        //***************rar request****************//

        binding.fileName7.text = "file.rar"
        binding.startCancelButton7.setOnClickListener {
            if (binding.startCancelButton7.text.equals("Start")) {
                downloadId7 = oziDownloader.enqueue(request7,
                    onStart = {
                        binding.status7.text = "Started"
                        binding.startCancelButton7.text = "Cancel"
                        binding.resumePauseButton7.isEnabled = true
                        binding.resumePauseButton7.visibility = View.VISIBLE
                    },
                    onPause = {
                        binding.status7.text = "Paused"
                    },
                    onProgress = {
                        binding.status7.text = "In Progress"
                        binding.progressBar7.progress = it
                        binding.progressText7.text = "$it%"
                    },
                    onCompleted = {
                        binding.status7.text = "Completed"
                        binding.progressText7.text = "100%"
                        binding.startCancelButton7.visibility = View.GONE
                        binding.resumePauseButton7.visibility = View.GONE
                    },
                    onError = {
                        binding.status7.text = "Error : $it"
                        binding.resumePauseButton7.visibility = View.GONE
                        binding.progressBar7.progress = 0
                        binding.progressText7.text = "0%"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId7)
                binding.startCancelButton7.text = "Start"
            }
        }

        binding.resumePauseButton7.setOnClickListener{
            if (oziDownloader.status(downloadId7) == Status.PAUSED) {
                binding.resumePauseButton7.text = "Pause"
                oziDownloader.resume(downloadId7)
            } else {
                binding.resumePauseButton7.text = "Resume"
                oziDownloader.pause(downloadId7)
            }
        }


        //***************zip request****************//

        binding.fileName8.text = "file.zip"
        binding.startCancelButton8.setOnClickListener {
            if (binding.startCancelButton8.text.equals("Start")) {
                downloadId8 = oziDownloader.enqueue(request8,
                    onStart = {
                        binding.status8.text = "Started"
                        binding.startCancelButton8.text = "Cancel"
                        binding.resumePauseButton8.isEnabled = true
                        binding.resumePauseButton8.visibility = View.VISIBLE
                    },
                    onPause = {
                        binding.status8.text = "Paused"
                    },
                    onProgress = {
                        binding.status8.text = "In Progress"
                        binding.progressBar8.progress = it
                        binding.progressText8.text = "$it%"
                    },
                    onCompleted = {
                        binding.status8.text = "Completed"
                        binding.progressText8.text = "100%"
                        binding.startCancelButton8.visibility = View.GONE
                        binding.resumePauseButton8.visibility = View.GONE
                    },
                    onError = {
                        binding.status8.text = "Error : $it"
                        binding.resumePauseButton8.visibility = View.GONE
                        binding.progressBar8.progress = 0
                        binding.progressText8.text = "0%"
                    }
                )
            } else {
                oziDownloader.cancel(downloadId8)
                binding.startCancelButton8.text = "Start"
            }
        }

        binding.resumePauseButton8.setOnClickListener{
            if (oziDownloader.status(downloadId8) == Status.PAUSED) {
                binding.resumePauseButton8.text = "Pause"
                oziDownloader.resume(downloadId8)
            } else {
                binding.resumePauseButton8.text = "Resume"
                oziDownloader.pause(downloadId8)
            }
        }


    }
}