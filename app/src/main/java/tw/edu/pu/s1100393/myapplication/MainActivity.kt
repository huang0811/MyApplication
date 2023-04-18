package tw.edu.pu.s1100393.myapplication

import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, View.OnTouchListener {
    var size:Float = 24f
    lateinit var gDetector: GestureDetector
    lateinit var txv: TextView
    var count:Int = 0
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img1.setOnTouchListener(this)
        img2.setOnTouchListener(this)

        txv = findViewById(R.id.txv)
        txv.setTextColor(Color.parseColor("#eeee00"))
        txv.setBackgroundColor(Color.BLUE)
        txv.setTypeface(
            Typeface.createFromAsset(assets,
                "font/HanyiSentyRubber.ttf"))
        txv.getBackground().setAlpha(50)  //0~255透明度值
        gDetector = GestureDetector(this, this)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        txv.text = "黃鈺慈的個人介紹"
        gDetector.onTouchEvent(event)
        return true
    }

    fun name(v:View){
        val txv:TextView = findViewById(R.id.txv)
        findViewById<TextView>(R.id.txv).setTextSize(size)
        var myname:String
        myname = "哈摟，我是黃鈺慈，關於我的更多資訊都藏在網頁裡，來尋找看看吧"
        txv.setText(myname)
    }

    override fun onDown(p0: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        txv.text = "喜歡出去玩"
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        txv.text = "喜歡聽音樂"
        return true
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        if (distanceY >= 0){
            txv.text = "生日：2003/08/11(民國92年)"
        }
        else{
            txv.text = "不暴躁的獅子座"
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        txv.text = "還喜歡拍風景跟人"
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (e1.x <= e2.x){
            txv.text = "我家的貓貓狗狗"
            count++
            if(count>5){count=0}
        }
        else{
            txv.text = "基努、小虎跟肉丸一家人"
            count--
            if(count<0){count=5}
        }
        var res:Int = getResources().getIdentifier("pu" + count.toString(),
            "drawable", getPackageName())
        findViewById<LinearLayout>(R.id.bg).setBackgroundResource(res)
        return true
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v==img1){
            txv.text = "小虎"
        }
        else{
            txv.text = "肉丸"
        }
        if (event?.action == MotionEvent.ACTION_MOVE){
            v?.x = event.rawX- v!!.width/2
            v?.y = event.rawY- v!!.height/2
        }

        var r1: Rect = Rect(img1.x.toInt(), img1.y.toInt(),
            img1.x.toInt() + img1.width, img1.y.toInt() + img1.height)
        var r2: Rect = Rect(img2.x.toInt(), img2.y.toInt(),
            img2.x.toInt() + img2.width, img2.y.toInt() + img2.height)

        if(r1.intersect(r2)) {
            txv.text = "打架日常"
        }
        return true
    }
}