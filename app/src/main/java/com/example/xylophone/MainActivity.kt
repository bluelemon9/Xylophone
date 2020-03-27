package com.example.xylophone

import android.content.pm.ActivityInfo
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val soundPool = SoundPool.Builder().setMaxStreams(8).build()

    private val sounds = listOf(
        Pair(R.id.do1, R.raw.do1),
        Pair(R.id.re, R.raw.re),
        Pair(R.id.mi, R.raw.mi),
        Pair(R.id.fa, R.raw.fa),
        Pair(R.id.sol, R.raw.sol),
        Pair(R.id.la, R.raw.la),
        Pair(R.id.si, R.raw.si),
        Pair(R.id.do2, R.raw.do2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE    // 화면 가로 모드 고정
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sounds.forEach{tune(it)}    // sounds 리스트의 요소를 하나씩 꺼내서 tune()에 전달
    }

    // 음판에 동적으로 클릭 이벤트 정의
    private fun tune(pitch: Pair<Int, Int>){
        val soundId = soundPool.load(this, pitch.second, 1)     // 음원의 ID 얻음
        findViewById<TextView>(pitch.first).setOnClickListener{             // 텍스트뷰의 ID에 해당하는 뷰 얻음
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f) // 텍스트뷰 클릭 시 재생
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}
